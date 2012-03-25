package net.kurochenko.chartviz.backend;

import net.kurochenko.chartviz.backend.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.ParseException;

/**
 * @author Andrej Kuročenko <andrej@kurochenko.net>
 */
@Controller
@RequestMapping("/")
public class GraphController {

    @Autowired
    private ChartService chartService;

    @Autowired
    private Init init;


    @RequestMapping
    public @ResponseBody String index() throws IOException, ParseException {
        return "index";
    }

}
