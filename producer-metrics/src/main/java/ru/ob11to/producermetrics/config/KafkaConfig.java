package ru.ob11to.producermetrics.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;
import ru.ob11to.producermetrics.kafka.MetricsProducerChannel;

@Configuration
@EnableBinding({
        MetricsProducerChannel.class
})
public class KafkaConfig {
}
