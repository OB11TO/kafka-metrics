package ru.ob11to.consumermetrics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ob11to.consumermetrics.entity.SystemPerformanceMetric;

public interface SystemPerformanceMetricRepository extends JpaRepository<SystemPerformanceMetric, Long> {
}
