package net.kurochenko.chartviz.backend;

import net.kurochenko.chartviz.backend.chart.ChartCreator;
import net.kurochenko.chartviz.backend.entity.Chart;
import net.kurochenko.chartviz.backend.entity.ChartDTO;
import net.kurochenko.chartviz.backend.service.ChartDataService;
import net.kurochenko.chartviz.backend.service.ChartService;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.entity.StandardEntityCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author Andrej Kuročenko <andrej@kurochenko.net>
 */
@Controller
@RequestMapping("/")
public class GraphController {

    public static final int CHART_WIDTH = 800;
    public static final int CHART_HEIGHT = 500;
    public static final String TEMP_IMAGE = "graphImage.png";

    @Autowired
    private ChartService chartService;


    @RequestMapping
    public String index(Model model) throws IOException {
        Chart chart = chartService.findAll().get(0);
        ChartDTO chartDTO = chartService.findDTO(chart.getId());

        FileOutputStream os = new FileOutputStream(TEMP_IMAGE);
        ChartRenderingInfo info = writeChart(chartDTO, os);
        os.close();

        model.addAttribute("chart", chart);
        model.addAttribute("imagemap", ChartUtilities.getImageMap("chMap", info));

        return "index";
    }

    @RequestMapping("/graph-{id}.png")
    public void index(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        response.setContentType("image/png");

        Chart chart = chartService.find(id);
        writeChart(chartService.findDTO(chart.getId()), response.getOutputStream());
    }

    private ChartRenderingInfo writeChart(ChartDTO chartDTO, OutputStream os) {
        ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());

        try {
            ChartUtilities.writeChartAsPNG(
                    os,
                    new ChartCreator().create(chartDTO),
                    CHART_WIDTH,
                    CHART_HEIGHT,
                    info
            );
        } catch (IOException e) {
            e.printStackTrace(); // TODO log

        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace(); // TODO log
                }
            }
        }
        return info;
    }

}
