package net.kurochenko.chartviz.backend.dao;

import net.kurochenko.chartviz.backend.AbstractSpringRunner;
import net.kurochenko.chartviz.backend.entity.Chart;
import net.kurochenko.chartviz.backend.entity.ChartData;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Andrej Kuročenko <kurochenko@mail.muni.cz>
 */
public class ChartDataDAOTest extends AbstractSpringRunner {

    @Autowired
    private ChartDAO chartDAO;

    @Autowired
    private ChartDataDAO chartDataDAO;

    private Chart chart1;
    private Chart chart2;
    private ChartData chartData;


    @Before
    public void setUp() {
        chart1 = new Chart();
        chart1.setName("Euro");
        chart1.setUnit("EUR");

        chart2 = new Chart();
        chart2.setName("Dollar");
        chart2.setUnit("USD");

        chartDAO.create(chart1);
        chartDAO.create(chart2);

        chartData = new ChartData();
        chartData.setTime(new Date(1332589567506L));
        chartData.setValue(BigDecimal.valueOf(24.589));
        chartData.setChart(chart1);
    }

    @Test
    public void testCreate() {
        chartDataDAO.create(chartData);
        assertNotNull(chartData.getId());

        ChartData retrieved = chartDataDAO.find(chartData.getId());
        assertEquals(1332589567506L, retrieved.getTime().getTime());
        assertEquals(BigDecimal.valueOf(24.589), retrieved.getValue());
        assertEquals(chart1, retrieved.getChart());
        assertEquals(chartData, chartDataDAO.find(chartData.getId()));
    }

    @Test
    public void testFind() {
        assertNull(chartDataDAO.find(Long.MAX_VALUE));
        chartDataDAO.create(chartData);
        assertEquals(chartData, chartDataDAO.find(chartData.getId()));
    }

    @Test
    public void testRemoveByChart() {
        ChartData chartData2 = new ChartData();
        chartData2.setTime(new Date(1332589567500L));
        chartData2.setValue(BigDecimal.valueOf(25.589));
        chartData2.setChart(chart1);

        ChartData chartData3 = new ChartData();
        chartData3.setTime(new Date(1332589567509L));
        chartData3.setValue(BigDecimal.valueOf(26.589));
        chartData3.setChart(chart2);

        chartDataDAO.create(chartData);
        chartDataDAO.create(chartData2);
        chartDataDAO.create(chartData3);

        chartDataDAO.removeByChart(chart1);
        assertTrue(chartDataDAO.findAll(chart1).isEmpty());
        assertTrue(chartDataDAO.findAll(chart2).contains(chartData3));
        assertEquals(1, chartDataDAO.findAll(chart2).size());
    }

    @Test
    public void testFindAll() {
        ChartData chartData2 = new ChartData();
        chartData2.setTime(new Date(1332589567500L));
        chartData2.setValue(BigDecimal.valueOf(25.589));
        chartData2.setChart(chart1);

        ChartData chartData3 = new ChartData();
        chartData3.setTime(new Date(1332589567509L));
        chartData3.setValue(BigDecimal.valueOf(26.589));
        chartData3.setChart(chart2);

        chartDataDAO.create(chartData);
        chartDataDAO.create(chartData2);
        chartDataDAO.create(chartData3);

        assertTrue(chartDataDAO.findAll(chart1).contains(chartData));
        assertTrue(chartDataDAO.findAll(chart1).contains(chartData2));
        assertEquals(2, chartDataDAO.findAll(chart1).size());
        assertTrue(chartDataDAO.findAll(chart2).contains(chartData3));
        assertEquals(1, chartDataDAO.findAll(chart2).size());
    }

    @Test
    public void testFindRange() {
        chartData.setTime(new Date(1332589567500L));
        
        ChartData chartData2 = new ChartData();
        chartData2.setTime(new Date(1332589567510L));
        chartData2.setValue(BigDecimal.valueOf(25.589));
        chartData2.setChart(chart1);

        ChartData chartData3 = new ChartData();
        chartData3.setTime(new Date(1332589567520L));
        chartData3.setValue(BigDecimal.valueOf(26.589));
        chartData3.setChart(chart1);

        ChartData chartData4 = new ChartData();
        chartData4.setTime(new Date(1332589567516L));
        chartData4.setValue(BigDecimal.valueOf(27.589));
        chartData4.setChart(chart2);

        chartDataDAO.create(chartData);
        chartDataDAO.create(chartData2);
        chartDataDAO.create(chartData3);
        chartDataDAO.create(chartData4);

        List<ChartData> range = chartDataDAO.findRange(chart1, new Date(1332589567489L), new Date(1332589567499L));
        assertTrue(range.isEmpty());

        range = chartDataDAO.findRange(chart1, new Date(1332589567499L), new Date(1332589567501L));
        assertTrue(range.contains(chartData));
        assertFalse(range.contains(chartData2));
        assertFalse(range.contains(chartData3));

        range = chartDataDAO.findRange(chart1, new Date(1332589567500L), new Date(1332589567520L));
        assertTrue(range.contains(chartData));
        assertTrue(range.contains(chartData2));
        assertTrue(range.contains(chartData3));
        assertFalse(range.contains(chartData4)); // belongs to other chart

        range = chartDataDAO.findRange(chart1, new Date(1332589567516L), new Date(1332589567521L));
        assertFalse(range.contains(chartData));
        assertFalse(range.contains(chartData2));
        assertTrue(range.contains(chartData3));
        assertFalse(range.contains(chartData4)); // belongs to other chart

        range = chartDataDAO.findRange(chart1, new Date(1332589567521L), new Date(1332589567530L));
        assertTrue(range.isEmpty());
    }

}
