package ru.ob11to.consumermetrics.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ob11to.consumermetrics.dto.SystemPerformanceMetricReadDto;
import ru.ob11to.consumermetrics.entity.SystemPerformanceMetric;
import ru.ob11to.consumermetrics.mapper.SystemPerformanceMetricMapper;
import ru.ob11to.consumermetrics.repository.SystemPerformanceMetricRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SystemPerformanceMetricService {

    private final SystemPerformanceMetricRepository systemPerformanceMetricRepository;
    private final SystemPerformanceMetricMapper mapper;

    @Transactional
    public List<SystemPerformanceMetric> getAllMetrics() {
        log.info("Get all metrics");
        return systemPerformanceMetricRepository.findAll();
    }

    @Transactional
    public Optional<SystemPerformanceMetricReadDto> getMetricById(Long id) {
        log.info("Get metric with id : {}", id);
        return systemPerformanceMetricRepository.findById(id)
                .map(mapper::toDto);
    }
}