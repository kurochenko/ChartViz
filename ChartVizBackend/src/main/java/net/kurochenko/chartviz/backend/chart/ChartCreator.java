package net.kurochenko.chartviz.backend.chart;

import net.kurochenko.chartviz.backend.entity.ChartDTO;
import net.kurochenko.chartviz.backend.entity.ChartData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.labels.CustomXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;

import java.awt.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrej Kuročenko <kurochenko@mail.muni.cz>
 */
public class ChartCreator {

    /** Date format of time on domain axis */
    public static final String AXIS_DATE_FORMAT = "MM/d";

    /** Tooltips of chart points */
    private List toolTips = new ArrayList();


    public JFreeChart create(ChartDTO chartDTO) {

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                chartDTO.getName(),
                chartDTO.getDomainAxeName(),
                chartDTO.getRangeAxeName(),
                createDataSet(chartDTO),
                true,
                true,
                false
        );

        
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setRenderer(getItemRenderer());
        plot.setBackgroundPaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));

        plot.setDomainCrosshairVisible(true);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.getDomainAxis().setVerticalTickLabels(true);

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, 1));
        axis.setDateFormatOverride(new SimpleDateFormat(AXIS_DATE_FORMAT));

        plot.setRangeGridlinePaint(Color.lightGray);
        plot.setRangeCrosshairVisible(true);
        
        return chart;
    }

    private StandardXYItemRenderer getItemRenderer() {
        StandardXYItemRenderer plotRenderer = new StandardXYItemRenderer(StandardXYItemRenderer.SHAPES_AND_LINES, getTooltipGenerator(), null);
        plotRenderer.setSeriesShapesFilled(0, true);
        plotRenderer.setSeriesPaint(0, Color.blue);
        plotRenderer.setSeriesStroke(0, new BasicStroke(2));
        return plotRenderer;
    }

    private CustomXYToolTipGenerator getTooltipGenerator() {
        CustomXYToolTipGenerator toolTipGenerator = new CustomXYToolTipGenerator();
        toolTipGenerator.addToolTipSeries(toolTips);
        return toolTipGenerator;
    }

    private XYDataset createDataSet(ChartDTO chartDTO) {
        TimeSeries timeSeries = new TimeSeries(chartDTO.getName());

        for (ChartData chartData : chartDTO.getData()) {
            BigDecimal value = chartData.getValue();

            timeSeries.add(new Day(chartData.getTime()), value);
            toolTips.add(value.toString());
        }

        return new TimeSeriesCollection(timeSeries);
    }
}
