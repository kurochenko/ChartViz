package net.kurochenko.chartviz.backend;

import net.kurochenko.chartviz.backend.entity.Chart;
import net.kurochenko.chartviz.backend.entity.ChartData;
import net.kurochenko.chartviz.backend.service.ChartDataService;
import net.kurochenko.chartviz.backend.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Transactional
    public void fillDB() throws ParseException {
        if (chartService.findAll().isEmpty()) {

            Chart chart = new Chart();
            chart.setName("EUR/CZK exchange rate");
            chart.setDomainAxeName("DAY");
            chart.setRangeAxeName("EUR/CZK");
            chart.setUnit("EUR");
            chartService.create(chart);

            Map<String, String> values  = getValues();
            List<ChartData> dataList = new ArrayList<ChartData>();

            for (String date : values.keySet()) {
                ChartData data = new ChartData();
                data.setChart(chart);
                data.setValue(new BigDecimal(values.get(date)));

                SimpleDateFormat sdf = new SimpleDateFormat("d.M.y");
                data.setTime(sdf.parse(date));
                dataList.add(data);
            }

            chartDataService.createList(dataList);
        }
    }
    
    public Map<String, String> getValues() {
        return new HashMap<String, String>(){{
            put("23.3.2012","24.740");
            put("22.3.2012","24.780");
            put("21.3.2012","24.480");
            put("20.3.2012","24.490");
            put("19.3.2012","24.530");
            put("16.3.2012","24.540");
            put("15.3.2012","24.610");
            put("14.3.2012","24.550");
            put("13.3.2012","24.600");
            put("12.3.2012","24.710");
            put("9.3.2012","24.840");
            put("8.3.2012","24.780");
            put("7.3.2012","24.890");
            put("6.3.2012","24.830");
            put("5.3.2012","24.720");
            put("2.3.2012","24.830");
            put("1.3.2012","24.820");
            put("29.2.2012","24.930");
            put("28.2.2012","24.950");
            put("27.2.2012","25.020");
            put("24.2.2012","24.980");
            put("23.2.2012","25.160");
            put("22.2.2012","25.150");
            put("21.2.2012","24.920");
            put("20.2.2012","25.000");
            put("17.2.2012","25.030");
            put("16.2.2012","25.330");
            put("15.2.2012","25.080");
            put("14.2.2012","25.070");
            put("13.2.2012","25.120");
            put("10.2.2012","25.320");
            put("9.2.2012","25.040");
            put("8.2.2012","24.810");
            put("7.2.2012","24.970");
            put("6.2.2012","24.970");
            put("3.2.2012","25.130");
            put("2.2.2012","25.180");
            put("1.2.2012","25.300");
            put("31.1.2012","25.250");
            put("30.1.2012","25.180");
            put("27.1.2012","25.130");
            put("26.1.2012","25.170");
            put("25.1.2012","25.350");
            put("24.1.2012","25.340");
            put("23.1.2012","25.470");
            put("20.1.2012","25.340");
            put("19.1.2012","25.340");
            put("18.1.2012","25.690");
            put("17.1.2012","25.640");
            put("16.1.2012","25.540");
        }};
    }
}
