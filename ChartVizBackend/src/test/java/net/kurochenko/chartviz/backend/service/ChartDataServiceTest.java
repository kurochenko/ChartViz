package net.kurochenko.chartviz.backend.service;

import net.kurochenko.chartviz.backend.AbstractMockInit;
import net.kurochenko.chartviz.backend.dao.ChartDataDAO;
import net.kurochenko.chartviz.backend.entity.Chart;
import net.kurochenko.chartviz.backend.entity.ChartData;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author Andrej Kuročenko <andrej@kurochenko.net>
 */
public class ChartDataServiceTest extends AbstractMockInit {

    private ChartDataServiceImpl chartDataService;
    @Mock private ChartDataDAO chartDataDAO;

    private Chart chart = new Chart();
    private ChartData chartData = new ChartData();

    
    @Before
    public void setUp() {
        chartDataService = new ChartDataServiceImpl();
        chartDataService.setChartDataDAO(chartDataDAO);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNull() {
        chartDataService.create(null);
    }

    @Test
    public void testCreate() {
        chartDataService.create(chartData);
        verify(chartDataDAO).create(chartData);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindAllNull() {
        chartDataService.findAll(null);
    }

    @Test
    public void testFindAll() {
        chartDataService.findAll(chart);
        verify(chartDataDAO).findAll(chart);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindRange2NullChart() {
        Date from = new Date(1332589567500L);
        Date to   = new Date(1332589567510L);
        chartDataService.findRange(null, from, to);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindRange2NullFrom() {
        Date to = new Date(1332589567510L);
        chartDataService.findRange(chart, null, to);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindRange2NullTo() {
        Date from = new Date(1332589567500L);
        chartDataService.findRange(chart, from, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindRange2FromAfterTo() {
        Date from = new Date(1332589567510L);
        Date to   = new Date(1332589567500L);
        chartDataService.findRange(chart, from, to);
    }

    @Test
    public void testFindRange2() {
        Date from = new Date(1332589567500L);
        Date to   = new Date(1332589567510L);
        chartDataService.findRange(chart, from, to);
        verify(chartDataDAO).findRange(chart, from, to);
    }
}
