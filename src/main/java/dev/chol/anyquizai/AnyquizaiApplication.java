package dev.chol.anyquizai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = {"dev.chol.anyquizai"})
public class AnyquizaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnyquizaiApplication.class, args);
    }

}
