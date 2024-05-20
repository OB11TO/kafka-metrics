package ru.ob11to.consumermetrics.dto;

import java.time.ZonedDateTime;

public record SystemPerformanceMetricReadDto(String cpuUsage,
                                             String memoryUsage,
                                             String diskUsage,
                                             ZonedDateTime createdAt) {
}
