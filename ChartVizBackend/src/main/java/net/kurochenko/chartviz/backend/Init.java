package net.kurochenko.chartviz.backend;

import net.kurochenko.chartviz.backend.entity.Chart;
import net.kurochenko.chartviz.backend.entity.ChartData;
import net.kurochenko.chartviz.backend.service.ChartDataService;
import net.kurochenko.chartviz.backend.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @PostConstruct
    public void fillDB() throws IOException, ParseException {
        if (chartService.findAll().isEmpty()) {
            String file = "/home/kurochenko/vals";
            BufferedReader reader = new BufferedReader(new FileReader(file));

            Chart chart = new Chart();
            chart.setName("Vývoj kurzu eura");
            chart.setUnit("EUR");
            chartService.create(chart);



            List<ChartData> dataList = new ArrayList<ChartData>();
            String line;
            while ((line = reader.readLine()) != null) {
                String [] values = line.split(";");

                ChartData data = new ChartData();
                data.setChart(chart);
                data.setValue(new BigDecimal(values[1].trim()));

                SimpleDateFormat sdf = new SimpleDateFormat("d.M.y");
                data.setTime(sdf.parse(values[0].trim()));
                dataList.add(data);
            }

            chartDataService.createList(dataList);
        }
    }

}
