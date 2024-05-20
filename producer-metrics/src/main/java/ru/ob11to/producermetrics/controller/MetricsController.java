package ru.ob11to.producermetrics.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ob11to.producermetrics.dto.SystemPerformanceMetric;
import ru.ob11to.producermetrics.kafka.MetricsProcedure;

@RestController
@RequiredArgsConstructor
public class MetricsController {

    private static final Logger log = LoggerFactory.getLogger(MetricsController.class);
    private final MetricsProcedure metricsProcedure;

    @PostMapping("/metrics")
    public ResponseEntity<String> receiveMetrics(@RequestBody SystemPerformanceMetric metrics) {
        log.info("Received metrics: {}", metrics);
        metricsProcedure.send(metrics);
        return ResponseEntity.status(HttpStatus.CREATED).body("Metrics delivered to kafka successfully");
    }
}
