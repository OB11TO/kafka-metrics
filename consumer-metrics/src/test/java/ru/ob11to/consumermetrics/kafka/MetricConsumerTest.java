package ru.ob11to.consumermetrics.kafka;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.ob11to.consumermetrics.dto.SystemPerformanceMetricDto;
import ru.ob11to.consumermetrics.entity.SystemPerformanceMetric;
import ru.ob11to.consumermetrics.repository.SystemPerformanceMetricRepository;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class MetricConsumerTest {

    @Mock
    private SystemPerformanceMetricRepository metricRepository;

    private MetricConsumer metricConsumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        metricConsumer = new MetricConsumer(metricRepository);
    }

    @Test
    void testHandle() {
        SystemPerformanceMetricDto payload = new SystemPerformanceMetricDto("50.0", "80.0", "70.0");
        metricConsumer.handle(payload);
        verify(metricRepository, times(1)).save(any(SystemPerformanceMetric.class));
    }
}
