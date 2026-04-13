package ch.lonelyporter.statsapp.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Statistic {

    private String id;
    private String title;
    private String reverse;
    private String xAxisName;
    private String yAxisName;
    private List<String> xAxisValues;
    private List<String> yAxisValues;
}
