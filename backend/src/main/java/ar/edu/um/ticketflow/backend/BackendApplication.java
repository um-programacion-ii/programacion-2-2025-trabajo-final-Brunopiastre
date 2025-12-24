package ar.edu.um.ticketflow.backend;

import ar.edu.um.ticketflow.backend.config.CatedraProperties; // <--- IMPORTANTE
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties; // <--- IMPORTANTE
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(CatedraProperties.class)  
public class BackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(BackendApplication.class, args);
  }

}
