package ar.edu.um.ticketflow.proxy.kafka;

import ar.edu.um.ticketflow.proxy.config.ProxyProperties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.scheduler.Schedulers;

import java.util.Map;

@Component
public class EventosConsumer {
    private final WebClient webClient;
    private final ProxyProperties props;

    public EventosConsumer(WebClient webClient, ProxyProperties props) {
        this.webClient = webClient;
        this.props = props;
    }

    @KafkaListener(topics = "#{'${kafka.topic-eventos}'}", groupId = "#{'${kafka.group-id}'}")
    public void onMessage(ConsumerRecord<String, String> record) {
        // Forward notification to backend
        try {
            webClient.post()
                    .uri(props.getBackendCallbackUrl())
                    .header("X-Internal-Token", props.getBackendInternalToken())
                    .bodyValue(Map.of(
                            "topic", record.topic(),
                            "key", record.key(),
                            "value", record.value(),
                            "partition", record.partition(),
                            "offset", record.offset()
                    ))
                    .retrieve()
                    .toBodilessEntity()
                    .publishOn(Schedulers.boundedElastic())
                    .subscribe();
        } catch (Exception ignored) { }
    }
}
