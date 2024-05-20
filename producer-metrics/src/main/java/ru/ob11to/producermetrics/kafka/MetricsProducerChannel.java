package ru.ob11to.producermetrics.kafka;


import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MetricsProducerChannel {
    String CHANNEL = "metricsTopic";

    @Output(CHANNEL)
    MessageChannel channel();
}