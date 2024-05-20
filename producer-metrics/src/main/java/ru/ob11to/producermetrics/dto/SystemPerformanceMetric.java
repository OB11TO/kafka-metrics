package ru.ob11to.producermetrics.dto;

public record SystemPerformanceMetric(Double cpuUsage,
                                      Double memoryUsage,
                                      Double diskUsage,
                                      Long timestamp) {
}
