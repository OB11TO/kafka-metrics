package ru.ob11to.consumermetrics.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import ru.ob11to.consumermetrics.dto.SystemPerformanceMetricReadDto;
import ru.ob11to.consumermetrics.entity.SystemPerformanceMetric;
import ru.ob11to.consumermetrics.service.SystemPerformanceMetricService;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class SystemPerformanceMetricControllerTest {

    @Mock
    private SystemPerformanceMetricService service;

    private SystemPerformanceMetricController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new SystemPerformanceMetricController(service);
    }

    @Test
    void testGetAllMetrics() {
        List<SystemPerformanceMetric> metrics = Collections.singletonList(new SystemPerformanceMetric());

        when(service.getAllMetrics()).thenReturn(metrics);

        ResponseEntity<List<SystemPerformanceMetric>> response = controller.getAllMetrics();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(metrics, response.getBody());
    }

    @Test
    void testGetMetricById_Exists() {
        SystemPerformanceMetricReadDto metricDto = new SystemPerformanceMetricReadDto(
                "50.0", "80.0", "70.0", ZonedDateTime.now());
        Long id = 1L;

        when(service.getMetricById(id)).thenReturn(Optional.of(metricDto));

        ResponseEntity<SystemPerformanceMetricReadDto> response = controller.getMetricById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(metricDto, response.getBody());
    }

    @Test
    void testGetMetricById_NotFound() {
        Long id = 1L;
        when(service.getMetricById(id)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> controller.getMetricById(id));
    }
}
