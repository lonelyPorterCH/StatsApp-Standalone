package ch.lonelyporter.statsapp.web.controller;

import ch.lonelyporter.statsapp.persistence.StatisticRepository;
import ch.lonelyporter.statsapp.web.model.Statistic;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/stats")
public class StatsController {

    private final StatisticRepository repository;

    @GetMapping
    public String stats(Model model) throws IOException {
        model.addAttribute("statistics", repository.findAll());
        return "overview";
    }

    @PostMapping("/{id}/datapoint")
    @ResponseBody
    public ResponseEntity<Void> addDataPoint(@PathVariable String id,
                                             @RequestBody Statistic.DataPoint dataPoint) throws IOException {
        Statistic statistic = repository.findById(id);
        statistic.getDataPoints().add(dataPoint);
        repository.save(statistic);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleIOException(IOException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }
}
