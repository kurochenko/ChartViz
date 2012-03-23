package net.kurochenko.chartviz.backend.dao;

import net.kurochenko.chartviz.backend.entity.Chart;

import java.util.List;

/**
 * @author Andrej Kuročenko <kurochenko@mail.muni.cz>
 */
public interface ChartDAO {
    void create(Chart chart);

    void edit(Chart chart);

    void remove(Chart chart);

    Chart find(Long id);

    List<Chart> findAll();
}
