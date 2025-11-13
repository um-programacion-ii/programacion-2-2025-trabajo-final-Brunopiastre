package ar.edu.um.ticketflow.backend.logs;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class StartupLogger {

    private static final Logger log = LoggerFactory.getLogger(StartupLogger.class);

    @PostConstruct
    public void start() {
        log.info("Backend iniciado correctamente");
    }
}
