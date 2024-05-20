package ru.ob11to.producermetrics.dto;

public record SystemPerformanceMetricDto(String cpuUsage,
                                         String memoryUsage,
                                         String diskUsage) {
}
