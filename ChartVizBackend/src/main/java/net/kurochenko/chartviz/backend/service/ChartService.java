package net.kurochenko.chartviz.backend.service;

import net.kurochenko.chartviz.backend.entity.Chart;

import java.util.List;

/**
 * @author Andrej Kuročenko <kurochenko@mail.muni.cz>
 */
public interface ChartService {
    public void create(Chart chart);
    public void edit(Chart chart);
    public void remove(Chart chart);
    public Chart find(Long id);
    public List<Chart> findAll();
}
