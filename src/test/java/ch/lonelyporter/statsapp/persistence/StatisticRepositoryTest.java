package ch.lonelyporter.statsapp.persistence;

import ch.lonelyporter.statsapp.StorageProperties;
import ch.lonelyporter.statsapp.web.model.Statistic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StatisticRepositoryTest {

    @TempDir
    Path tempDir;

    private StatisticRepository repository;

    @BeforeEach
    void setUp() {
        StorageProperties props = new StorageProperties();
        props.setStoragePath(tempDir.toString());
        repository = new StatisticRepository(props, new ObjectMapper());
    }

    @Test
    void save_writesJsonFile() throws IOException {
        Statistic statistic = new Statistic(
                "test-chart",
                "My Chart",
                "Date",
                "Price",
                List.of("2024-01-01", "2024-01-15", "2024-02-01"),
                List.of("100", "150", "130")
        );

        repository.save(statistic);

        Path expectedFile = tempDir.resolve("test-chart.json");
        assertThat(expectedFile).exists();
    }

    @Test
    void save_thenFindById_returnsEquivalentStatistic() throws IOException {
        Statistic original = new Statistic(
                "test-chart",
                "My Chart",
                "Date",
                "Price",
                List.of("2024-01-01", "2024-01-15", "2024-02-01"),
                List.of("100", "150", "130")
        );

        repository.save(original);
        Statistic loaded = repository.findById("test-chart");

        assertThat(loaded.getId()).isEqualTo("test-chart");
        assertThat(loaded.getXAxisName()).isEqualTo("Date");
        assertThat(loaded.getYAxisName()).isEqualTo("Price");
        assertThat(loaded.getXAxisValues()).containsExactly("2024-01-01", "2024-01-15", "2024-02-01");
        assertThat(loaded.getYAxisValues()).containsExactly("100", "150", "130");
    }

    @Test
    void findAll_returnsAllSavedStatistics() throws IOException {
        repository.save(new Statistic("chart-1", "Chart 1", "Date", "Price",
                List.of("2024-01-01"), List.of("100")));
        repository.save(new Statistic("chart-2", "Chart 2", "Date", "Volume",
                List.of("2024-01-01"), List.of("200")));

        List<Statistic> all = repository.findAll();

        assertThat(all).hasSize(2);
        assertThat(all).extracting(Statistic::getId)
                .containsExactlyInAnyOrder("chart-1", "chart-2");
    }

    @Test
    void findAll_onEmptyFolder_returnsEmptyList() throws IOException {
        List<Statistic> all = repository.findAll();

        assertThat(all).isEmpty();
    }
}