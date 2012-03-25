package net.kurochenko.chartviz.backend.service;

import net.kurochenko.chartviz.backend.dao.ChartDataDAO;
import net.kurochenko.chartviz.backend.entity.Chart;
import net.kurochenko.chartviz.backend.entity.ChartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Andrej Kuročenko <kurochenko@mail.muni.cz>
 */
@Service
@Transactional
public class ChartDataServiceImpl implements ChartDataService {

    private ChartDataDAO chartDataDAO;


    @Autowired
    public void setChartDataDAO(ChartDataDAO chartDataDAO) {
        this.chartDataDAO = chartDataDAO;
    }

    @Override
    public void create(ChartData chartData) {
        if (chartData == null) {
            throw new IllegalArgumentException("Chart data are null");
        }
        
        chartDataDAO.create(chartData);
    }

    @Override
    public void createList(List<ChartData> chartDataList) {
        if (chartDataList == null) {
            throw new IllegalArgumentException("Chart data list is null");
        }

        chartDataDAO.createList(chartDataList);
    }

    @Override
    public List<ChartData> findAll(Chart chart) {
        if (chart == null) {
            throw new IllegalArgumentException("Chart is null");
        }
        
        return chartDataDAO.findAll(chart);
    }

    @Override
    public List<ChartData> findRange(Chart chart, Date from, Date to) {
        if (chart == null) {
            throw new IllegalArgumentException("Chart is null");
        }
        if (from == null) {
            throw new IllegalArgumentException("[From] date is null");
        }
        if (to == null) {
            throw new IllegalArgumentException("[To] date is null");
        }
        if (from.after(to)) {
            throw new IllegalArgumentException("[From] date should be before [To] date");
        }

        return chartDataDAO.findRange(chart, from, to);
    }
}
