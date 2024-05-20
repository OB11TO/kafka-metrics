package ru.ob11to.producermetrics.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.ob11to.producermetrics.dto.SystemPerformanceMetricDto;
import ru.ob11to.producermetrics.kafka.MetricsProcedure;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MetricsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MetricsProcedure metricsProcedure;

    @Test
    void testReceiveMetrics() throws Exception {
        MockitoAnnotations.openMocks(this);
        MetricsController metricsController = new MetricsController(metricsProcedure);
        mockMvc = MockMvcBuilders.standaloneSetup(metricsController).build();
        SystemPerformanceMetricDto metricsDto = new SystemPerformanceMetricDto("50.0", "80.0", "70.0");
        String metricsJson = new ObjectMapper().writeValueAsString(metricsDto);

        doNothing().when(metricsProcedure).send(any(SystemPerformanceMetricDto.class));

        mockMvc.perform(post("/metrics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(metricsJson))
                .andExpect(status().isCreated());

        verify(metricsProcedure, times(1)).send(any(SystemPerformanceMetricDto.class));
    }
}
