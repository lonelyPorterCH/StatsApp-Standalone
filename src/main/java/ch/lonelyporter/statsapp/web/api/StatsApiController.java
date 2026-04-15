package ch.lonelyporter.statsapp.web.api;

import ch.lonelyporter.statsapp.persistence.StatisticRepository;
import ch.lonelyporter.statsapp.web.model.Statistic;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stats")
public class StatsApiController {

    private final StatisticRepository repository;

    @PostMapping
    public ResponseEntity<Void> createStatistic(@RequestBody Statistic statistic) throws IOException {
        repository.save(statistic);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/datapoint")
    public ResponseEntity<Void> addDataPoint(@PathVariable String id,
                                             @RequestBody Statistic.DataPoint dataPoint) throws IOException {
        Statistic statistic = repository.findById(id);
        statistic.getDataPoints().add(dataPoint);
        repository.save(statistic);
        return ResponseEntity.ok().build();
    }
}
