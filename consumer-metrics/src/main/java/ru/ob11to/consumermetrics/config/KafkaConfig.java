package ru.ob11to.consumermetrics.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;
import ru.ob11to.consumermetrics.kafka.MetricsConsumerChannel;

@Configuration
@EnableBinding({
        MetricsConsumerChannel.class
})
public class KafkaConfig {
}
