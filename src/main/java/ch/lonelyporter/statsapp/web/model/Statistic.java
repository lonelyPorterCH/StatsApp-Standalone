package ch.lonelyporter.statsapp.web.model;

import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Statistic {
    private String id;
    private String title;
    private boolean reverse;
    private String xAxisName;
    private String yAxisName;
    private List<DataPoint> dataPoints;

    public String getDataPointsJson() {
        return dataPoints.stream()
                .map(dp -> "{\"x\":\"" + dp.getX() + "\",\"y\":\"" + dp.getY() + "\"}")
                .collect(Collectors.joining(",", "[", "]"));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @ToString
    public static class DataPoint {
        private String x;
        private String y;
    }
}