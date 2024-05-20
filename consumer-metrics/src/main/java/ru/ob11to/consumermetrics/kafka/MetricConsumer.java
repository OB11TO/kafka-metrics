package ru.ob11to.consumermetrics.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;
import ru.ob11to.consumermetrics.dto.SystemPerformanceMetricDto;
import ru.ob11to.consumermetrics.entity.SystemPerformanceMetric;
import ru.ob11to.consumermetrics.repository.SystemPerformanceMetricRepository;

import java.time.ZonedDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class MetricConsumer {

    private final SystemPerformanceMetricRepository metricRepository;

    @StreamListener(MetricsConsumerChannel.CHANNEL)
    public void handle(SystemPerformanceMetricDto payload) {
        log.info("Logging message <{}>", payload);
        SystemPerformanceMetric metric = SystemPerformanceMetric.builder()
                .createdAt(ZonedDateTime.now())
                .cpuUsage(payload.cpuUsage())
                .diskUsage(payload.diskUsage())
                .memoryUsage(payload.memoryUsage())
                .build();
        metricRepository.save(metric);
    }
}