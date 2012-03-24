package net.kurochenko.chartviz.backend.service;

import net.kurochenko.chartviz.backend.entity.Chart;
import net.kurochenko.chartviz.backend.entity.ChartData;

import java.util.Date;
import java.util.List;

/**
 * @author Andrej Kuročenko <kurochenko@mail.muni.cz>
 */
public interface ChartDataService {
    public void create(ChartData chartData);
    public List<ChartData> findAll(Chart chart);
    public List<ChartData> findRange(Chart chart, Date from, Date to);
    
}
