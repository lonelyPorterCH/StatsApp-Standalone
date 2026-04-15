package ch.lonelyporter.statsapp.web.api;

import ch.lonelyporter.statsapp.service.StatisticService;
import ch.lonelyporter.statsapp.web.model.Statistic;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stats")
public class StatsApiController {

    private final StatisticService statisticService;

    @PostMapping
    public ResponseEntity<Void> createStatistic(@RequestBody Statistic statistic) {
        statisticService.createStatistic(statistic);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/datapoint")
    public ResponseEntity<Void> addDataPoint(@PathVariable String id,
                                             @RequestBody Statistic.DataPoint dataPoint) {
        statisticService.addDataPoint(id, dataPoint);
        return ResponseEntity.ok().build();
    }
}
