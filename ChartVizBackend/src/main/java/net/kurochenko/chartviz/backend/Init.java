package net.kurochenko.chartviz.backend;

import net.kurochenko.chartviz.backend.entity.Chart;
import net.kurochenko.chartviz.backend.entity.ChartData;
import net.kurochenko.chartviz.backend.parser.ecb.EcbExchangeRateParser;
import net.kurochenko.chartviz.backend.parser.ecb.ExchangeRateDTO;
import net.kurochenko.chartviz.backend.service.ChartDataService;
import net.kurochenko.chartviz.backend.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Andrej Kuročenko <andrej@kurochenko.net>
 */
@Component
public class Init {

    @Autowired
    private ChartService chartService;

    @Autowired
    private ChartDataService chartDataService;

    @Autowired
    private EcbExchangeRateParser parser;

    @PostConstruct
    @Transactional
    public void fillDB() throws ParseException {
        if (chartService.findAll().isEmpty()) {

            Chart chart = new Chart();
            chart.setName("EUR/CZK exchange rate");
            chart.setDomainAxeName("DAY");
            chart.setRangeAxeName("EUR/CZK");
            chart.setUnit("EUR");
            chartService.create(chart);

            chartDataService.createList(dtoToList(chart, parser.parseAll()));
        }
    }

    private List<ChartData> dtoToList(Chart chart, ExchangeRateDTO dto) {
        List<ChartData> dataList = new ArrayList<ChartData>();
        for (Date date : dto.getRates().keySet()) {
            ChartData data = new ChartData();
            data.setTime(date);
            data.setChart(chart);

            data.setValue(dto.getRates().get(date).get("CZK"));
            dataList.add(data);
        }

        return dataList;
    }

    @Scheduled(cron = "0 5 0 * * *") // 5 minutes after midnight every day
    public void updateRates() {
        Chart chart = chartService.findAll().get(0);
        if (chart != null) {
            chartDataService.createList(dtoToList(chart, parser.parseActual()));
        }
    }

}
