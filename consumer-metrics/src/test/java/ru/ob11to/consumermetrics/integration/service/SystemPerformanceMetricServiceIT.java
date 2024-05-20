package ru.ob11to.consumermetrics.integration.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.ob11to.consumermetrics.dto.SystemPerformanceMetricReadDto;
import ru.ob11to.consumermetrics.entity.SystemPerformanceMetric;
import ru.ob11to.consumermetrics.integration.IntegrationTestBase;
import ru.ob11to.consumermetrics.service.SystemPerformanceMetricService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class SystemPerformanceMetricServiceIT extends IntegrationTestBase {

    @Autowired
    private SystemPerformanceMetricService systemPerformanceMetricService;

    @Test
    void getAllMetrics_shouldReturnAllMetrics() {
        List<SystemPerformanceMetric> metrics = systemPerformanceMetricService.getAllMetrics();
        assertThat(metrics).isNotEmpty();
        assertThat(metrics.size()).isEqualTo(10);
    }


    @Test
    void getMetricById_shouldReturnEmpty_whenIdDoesNotExist() {
        Long id = 999L;
        Optional<SystemPerformanceMetricReadDto> metricOptional = systemPerformanceMetricService.getMetricById(id);
        assertThat(metricOptional).isNotPresent();
    }
}
