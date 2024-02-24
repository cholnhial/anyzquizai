package dev.chol.anyquizai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class implementing the {@link WebMvcConfigurer} interface to customize date and time formatting
 * within a Spring Boot application. This class specifically configures the formatting of date and time values
 * using the ISO format.
 *
 * <p>The {@code addFormatters} method is overridden to register a {@link DateTimeFormatterRegistrar},
 * setting it to use the ISO format. This ensures consistent and standardized formatting of date and time values
 * throughout the application.
 */
@Configuration
public class DateTimeFormatConfiguration implements WebMvcConfigurer {

    /**
     * Overrides the {@link WebMvcConfigurer#addFormatters(FormatterRegistry)} method to customize date and time
     * formatting for the application.
     *
     * @param registry the {@link FormatterRegistry} to register custom formatters.
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // Create a DateTimeFormatterRegistrar
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();

        // Set to use ISO format
        registrar.setUseIsoFormat(true);

        // Register formatters with the provided registry
        registrar.registerFormatters(registry);
    }
}
