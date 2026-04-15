package ch.lonelyporter.statsapp.web.controller;

import ch.lonelyporter.statsapp.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/stats")
public class StatsController {

    private final StatisticService statisticService;

    @GetMapping
    public String stats(Model model) {
        model.addAttribute("statistics", statisticService.getStatistics());
        return "overview";
    }
}
