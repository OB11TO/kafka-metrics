package ru.ob11to.consumermetrics.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.ob11to.consumermetrics.dto.SystemPerformanceMetricReadDto;
import ru.ob11to.consumermetrics.entity.SystemPerformanceMetric;

@Mapper(componentModel = "spring")
public interface SystemPerformanceMetricMapper {

    @Mappings({
            @Mapping(target = "cpuUsage", source = "cpuUsage"),
            @Mapping(target = "memoryUsage", source = "memoryUsage"),
            @Mapping(target = "diskUsage", source = "diskUsage"),
            @Mapping(target = "createdAt", source = "createdAt"),
    })
    SystemPerformanceMetricReadDto toDto(SystemPerformanceMetric systemPerformanceMetric);
}