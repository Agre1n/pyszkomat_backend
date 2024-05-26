package net.springboot.pyszkomat_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PyszkomatBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PyszkomatBackendApplication.class, args);
    }

}
