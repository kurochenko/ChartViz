package net.kurochenko.chartviz.backend.service;

import net.kurochenko.chartviz.backend.entity.Chart;
import net.kurochenko.chartviz.backend.entity.ChartDTO;

import java.util.Date;
import java.util.List;

/**
 * @author Andrej Kuročenko <kurochenko@mail.muni.cz>
 */
public interface ChartService {
    public void create(Chart chart);
    public void edit(Chart chart);
    public void remove(Chart chart);
    public Chart find(Long id);
    public ChartDTO findDTO(Long id);
    public ChartDTO findDTORange(Long id, Date from, Date to);
    public List<Chart> findAll();
}
