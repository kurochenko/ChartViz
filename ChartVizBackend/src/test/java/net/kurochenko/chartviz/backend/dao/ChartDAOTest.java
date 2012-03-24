package net.kurochenko.chartviz.backend.dao;

import net.kurochenko.chartviz.backend.entity.Chart;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author Andrej Kuročenko <kurochenko@mail.muni.cz>
 */
public class ChartDAOTest extends AbstractDAOTest{

    @Autowired
    private ChartDAO chartDAO;

    private Chart chart;
    
    @Before
    public void setUp() {
        chart = new Chart();
        chart.setName("Euro");
        chart.setUnit("EUR");
    }
    
    @Test
    public void tetCreate() {
        chartDAO.create(chart);
        assertNotNull(chart.getId());
        Chart retrieved = chartDAO.find(chart.getId());
        assertEquals("Euro", retrieved.getName());
        assertEquals("EUR", retrieved.getUnit());
        assertEquals(chart, retrieved);
    }

    @Test
    public void tetEdit() {
        chartDAO.create(chart);
        
        chart.setName("Dollar");
        chart.setUnit("USD");

        chartDAO.edit(chart);

        Chart retrieved = chartDAO.find(chart.getId());
        assertEquals("Dollar", retrieved.getName());
        assertEquals("USD", retrieved.getUnit());
    }

    @Test
    public void tetRemove() {
        Chart chart2 = new Chart();
        chart2.setName("Dollar");
        chart2.setUnit("USD");

        chartDAO.create(chart);
        chartDAO.create(chart2);
        
        Long chartId = chart.getId();
        Long chart2Id = chart2.getId();

        chartDAO.remove(chart);
        assertNull(chartDAO.find(chartId));
        assertEquals(chart2, chartDAO.find(chart2Id));

        chartDAO.remove(chart2);
        assertNull(chartDAO.find(chartId));
        assertNull(chartDAO.find(chart2Id));
    }

    @Test
    public void tetFind() {
        assertNull(chartDAO.find(Long.MAX_VALUE));

        Chart chart2 = new Chart();
        chart2.setName("Dollar");
        chart2.setUnit("USD");

        chartDAO.create(chart);
        chartDAO.create(chart2);

        assertEquals(chart, chartDAO.find(chart.getId()));
        assertEquals(chart2, chartDAO.find(chart2.getId()));
    }

    @Test
    public void findAll() {
        Chart chart2 = new Chart();
        chart2.setName("Dollar");
        chart2.setUnit("USD");

        assertTrue(chartDAO.findAll().isEmpty());

        chartDAO.create(chart);
        assertTrue(chartDAO.findAll().contains(chart));
        assertEquals(1, chartDAO.findAll().size());

        chartDAO.create(chart2);
        assertTrue(chartDAO.findAll().contains(chart));
        assertTrue(chartDAO.findAll().contains(chart2));
        assertEquals(2, chartDAO.findAll().size());
    }
}
