package ch.lonelyporter.statsapp.web.controller;

import ch.lonelyporter.statsapp.persistence.StatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleIOException(IOException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }
}
