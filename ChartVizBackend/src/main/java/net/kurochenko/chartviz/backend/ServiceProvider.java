package net.kurochenko.chartviz.backend;

import net.kurochenko.chartviz.backend.service.ChartDataService;
import net.kurochenko.chartviz.backend.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Andrej Kuročenko <andrej@kurochenko.net>
 */
@Component
public class ServiceProvider {

    private static ChartService chartService;
    private static ChartDataService chartDataService;


    public static ChartService getChartService() {
        return chartService;
    }

    @Autowired
    public static void setChartService(ChartService chartService) {
        ServiceProvider.chartService = chartService;
    }

    public static ChartDataService getChartDataService() {
        return chartDataService;
    }

    @Autowired
    public static void setChartDataService(ChartDataService chartDataService) {
        ServiceProvider.chartDataService = chartDataService;
    }
}
