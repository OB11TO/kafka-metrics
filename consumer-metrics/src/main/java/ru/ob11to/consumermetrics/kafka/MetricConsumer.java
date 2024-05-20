package ru.ob11to.consumermetrics.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;
import ru.ob11to.consumermetrics.dto.Metrics;

@Slf4j
@Service
@RequiredArgsConstructor
public class MetricConsumer {

    @StreamListener(MetricsConsumerChannel.CHANNEL)
    public void handle(Metrics payload) {
        log.info("Logging message <{}>", payload);
    }
}