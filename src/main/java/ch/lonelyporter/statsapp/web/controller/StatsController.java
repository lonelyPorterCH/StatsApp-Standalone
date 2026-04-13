package ch.lonelyporter.statsapp.web.controller;

import ch.lonelyporter.statsapp.web.model.Statistic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/stats")
public class StatsController {

    @GetMapping
    public String stats(Model model) {
        model.addAttribute("statistics", getTempStats());
        return "overview";
    }

    private List<Statistic> getTempStats() {


        return List.of(
                new Statistic("foo", "Foo 1", "x", "y", List.of("2024-01-01", "2024-01-15"), List.of("1", "3")),
                new Statistic("foo2", "Foo 2", "x", "y", List.of("2024-01-01", "2024-01-15"), List.of("69, 420"))
        );
    }
}
