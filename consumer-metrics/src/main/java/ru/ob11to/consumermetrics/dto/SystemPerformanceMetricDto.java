package ru.ob11to.consumermetrics.dto;

public record SystemPerformanceMetricDto(String cpuUsage,
                                         String memoryUsage,
                                         String diskUsage) {
}
