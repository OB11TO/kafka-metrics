package ru.ob11to.consumermetrics.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MetricsConsumerChannel {
    String CHANNEL = "metricsTopic";

    @Input(CHANNEL)
    SubscribableChannel channel();
}