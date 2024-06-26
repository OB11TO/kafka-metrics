package ru.ob11to.producermetrics.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import ru.ob11to.producermetrics.dto.SystemPerformanceMetricDto;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MetricsProcedure {

    private final MetricsProducerChannel producer;

    public void send(SystemPerformanceMetricDto payload) {
        Message<SystemPerformanceMetricDto> message = MessageBuilder
                .withPayload(payload)
                .setHeaderIfAbsent("UUID", UUID.randomUUID())
                .build();
        producer.channel().send(message);
    }
}