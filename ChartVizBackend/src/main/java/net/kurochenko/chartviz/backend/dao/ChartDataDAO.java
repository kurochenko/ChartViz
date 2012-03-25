package net.kurochenko.chartviz.backend.dao;

import net.kurochenko.chartviz.backend.entity.Chart;
import net.kurochenko.chartviz.backend.entity.ChartData;

import java.util.Date;
import java.util.List;

/**
 * @author Andrej Kuročenko <kurochenko@mail.muni.cz>
 */
public interface ChartDataDAO {

    void create(ChartData data);

    void createList(List<ChartData> chartDataList);
    
    ChartData find(Long id);

    void removeByChart(Chart chart);

    List<ChartData> findAll(Chart chart);

    List<ChartData> findRange(Chart chart, Date from, Date to);
}
