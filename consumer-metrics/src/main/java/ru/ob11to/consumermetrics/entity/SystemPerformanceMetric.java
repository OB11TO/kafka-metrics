package ru.ob11to.consumermetrics.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(schema = "public", name = "system_performance_metrics")
public class SystemPerformanceMetric implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "cpu_usage", nullable = false)
    private String cpuUsage;

    @Column(name = "memory_usage", nullable = false)
    private String memoryUsage;

    @Column(name = "disk_usage", nullable = false)
    private String diskUsage;
}