package net.kurochenko.chartviz.backend.schedule;

import net.kurochenko.chartviz.backend.parser.ecb.ExchangeRateParser;
import net.kurochenko.chartviz.backend.service.ChartDataService;
import net.kurochenko.chartviz.backend.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author Andrej Kuročenko <kurochenko@mail.muni.cz>
 */
//@Component
public class CurrencyRatePuller {

    @Autowired
    @Qualifier("ecbExchangeRateParser")
    private ExchangeRateParser parser;

    @Autowired
    private ChartService chartService;

    @Autowired
    private ChartDataService chartDataService;


//    @PostConstruct
//    @Scheduled(cron = "0 5 0 * * *") // 5 minutes after midnight every day
    public void pullCurrencyRates() {
//        ExchangeRateDTO dto = parser.parse();
//
//        ChartData czk = new ChartData();
//        czk.setChart(chartService.findAll().get(0));
//        czk.setTime(dto.getDay());
//        czk.setValue(dto.getRates().get("CZK"));
//
//        chartDataService.create(c);
//
//
//        currencyRateService.updateRates(parser.parse());
    }

}
