package com.um.eventosproxy.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        // Deshabilitar auto-commit para permitir ack manual
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        // Habilitar confirmación manual (ACK)
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);

        // Asignar el manejador de reintentos al contenedor
        factory.setCommonErrorHandler(containerErrorHandler());

        return factory;
    }

    /**
     * Manejador de errores del CONTENEDOR (Internal).
     * Se encarga de los reintentos y el BackOff.
     */
    public DefaultErrorHandler containerErrorHandler() {
        return new DefaultErrorHandler(new FixedBackOff(1000L, 3));
    }

    /**
     * Manejador de errores del LISTENER (Requerido por @KafkaListener).
     * Este bean satisface la dependencia "kafkaErrorHandler" que pide tu consumidor.
     * Simplemente relanza la excepción para que el contenedor la maneje.
     */
    @Bean("kafkaErrorHandler")
    public KafkaListenerErrorHandler listenerErrorHandler() {
        return (message, exception) -> {
            // Relanzamos la excepción para que el DefaultErrorHandler (arriba) haga los reintentos
            throw exception;
        };
    }
}