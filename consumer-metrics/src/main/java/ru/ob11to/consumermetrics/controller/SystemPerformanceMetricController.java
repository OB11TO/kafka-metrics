package ru.ob11to.consumermetrics.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.ob11to.consumermetrics.dto.SystemPerformanceMetricReadDto;
import ru.ob11to.consumermetrics.entity.SystemPerformanceMetric;
import ru.ob11to.consumermetrics.service.SystemPerformanceMetricService;

import java.util.List;

@RestController
@RequestMapping("/metrics")
@RequiredArgsConstructor
@Tag(name = "Metrics", description = "Анализ метрик производительности системы")
public class SystemPerformanceMetricController {

    private final SystemPerformanceMetricService service;

    @Operation(summary = "Получение списка всех метрик производительности системы")
    @GetMapping
    public ResponseEntity<List<SystemPerformanceMetric>> getAllMetrics() {
        return ResponseEntity.ok(service.getAllMetrics());
    }

    @Operation(summary = "Получение конкретной метрики производительности системы по ее идентификатору")
    @GetMapping("/{id}")
    public ResponseEntity<SystemPerformanceMetricReadDto> getMetricById(@PathVariable Long id) {
        return service.getMetricById(id)
                .map(metric -> ResponseEntity.ok().body(metric))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}