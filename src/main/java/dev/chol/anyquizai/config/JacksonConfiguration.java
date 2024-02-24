package dev.chol.anyquizai.config;

import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for customizing Jackson ObjectMapper settings in a Spring Boot application.
 * Jackson is a widely used library for JSON processing in Java.
 * This class provides beans for configuring various Jackson modules to handle specific types and features.
 */
@Configuration
public class JacksonConfiguration {

    /**
     * Configures a Jackson module to support the Java Date and Time API (JSR-310).
     *
     * @return JavaTimeModule - the corresponding Jackson module for Java Date and Time API support.
     */
    @Bean
    public JavaTimeModule javaTimeModule() {
        return new JavaTimeModule();
    }

    /**
     * Configures a Jackson module to support Java 8 Optional and related types.
     *
     * @return Jdk8Module - the corresponding Jackson module for Java 8 Optional and related types support.
     */
    @Bean
    public Jdk8Module jdk8TimeModule() {
        return new Jdk8Module();
    }

    /**
     * Configures a Jackson module to support Hibernate types.
     * Enables the serialization of identifiers for lazy-not-loaded objects in Hibernate.
     *
     * @return Hibernate6Module - the corresponding Jackson module for Hibernate types support.
     */
    @Bean
    public Hibernate6Module hibernate6Module() {
        return new Hibernate6Module()
                .configure(Hibernate6Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS, true);
    }
}
