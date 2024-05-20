package ru.ob11to.producermetrics.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ob11to.producermetrics.dto.Metrics;
import ru.ob11to.producermetrics.kafka.MetricsProcedure;

@RestController
@RequiredArgsConstructor
public class MetricsController {

    private final MetricsProcedure metricsProcedure;

    @PostMapping("/metrics")
    public ResponseEntity<String> receiveMetrics(@RequestBody Metrics metrics) {
        metricsProcedure.send(metrics);
        return ResponseEntity.status(HttpStatus.CREATED).body("Metrics received successfully");
    }
}
