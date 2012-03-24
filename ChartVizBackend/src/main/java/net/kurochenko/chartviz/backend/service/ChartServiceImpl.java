package net.kurochenko.chartviz.backend.service;

import net.kurochenko.chartviz.backend.dao.ChartDAO;
import net.kurochenko.chartviz.backend.dao.ChartDataDAO;
import net.kurochenko.chartviz.backend.entity.Chart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Andrej Kuročenko <kurochenko@mail.muni.cz>
 */
@Service
@Transactional
public class ChartServiceImpl implements ChartService {

    private ChartDAO chartDAO;

    private ChartDataDAO chartDataDAO;


    @Autowired
    public void setChartDAO(ChartDAO chartDAO) {
        this.chartDAO = chartDAO;
    }

    @Autowired
    public void setChartDataDAO(ChartDataDAO chartDataDAO) {
        this.chartDataDAO = chartDataDAO;
    }

    @Override
    public void create(Chart chart) {
        if (chart == null) {
            throw new IllegalArgumentException("Chart is null");
        }

        chartDAO.create(chart);
    }

    @Override
    public void edit(Chart chart) {
        if (chart == null) {
            throw new IllegalArgumentException("Chart is null");
        }

        chartDAO.edit(chart);
    }

    @Override
    public void remove(Chart chart) {
        if (chart == null) {
            throw new IllegalArgumentException("Chart is null");
        }

        chartDataDAO.removeByChart(chart);
        chartDAO.remove(chart);
    }

    @Override
    @Transactional(readOnly = true)
    public Chart find(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Chart id is null");
        }

        return chartDAO.find(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Chart> findAll() {
        return chartDAO.findAll();
    }
}
