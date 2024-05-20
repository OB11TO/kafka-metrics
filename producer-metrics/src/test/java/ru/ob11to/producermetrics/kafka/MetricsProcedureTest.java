package ru.ob11to.producermetrics.kafka;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import ru.ob11to.producermetrics.dto.SystemPerformanceMetricDto;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MetricsProcedureTest {

    @Mock
    private MetricsProducerChannel producer;

    @Mock
    private MessageChannel messageChannel;

    @Test
    void testSend() {
        MockitoAnnotations.openMocks(this);
        MetricsProcedure metricsProcedure = new MetricsProcedure(producer);
        when(producer.channel()).thenReturn(messageChannel);
        SystemPerformanceMetricDto payload = new SystemPerformanceMetricDto("50.0", "80.0", "70.0");
        metricsProcedure.send(payload);
        verify(messageChannel, times(1)).send(any(Message.class));
    }
}
