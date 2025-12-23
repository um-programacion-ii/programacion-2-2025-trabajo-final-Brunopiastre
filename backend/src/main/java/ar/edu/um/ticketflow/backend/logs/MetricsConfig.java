package ar.edu.um.ticketflow.backend.logs;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {

    @Bean
    public Counter ventasCounter(MeterRegistry registry) {
        return Counter.builder("ticketflow_ventas_total")
                .description("Total de ventas realizadas")
                .register(registry);
    }
}
