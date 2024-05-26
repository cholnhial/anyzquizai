package dev.chol.anyquizai.config;

import dev.chol.anyquizai.web.filter.SpaWebFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<SpaWebFilter> spaWebFilter() {
        FilterRegistrationBean<SpaWebFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SpaWebFilter());
        registrationBean.addUrlPatterns("/*"); // Configure the URL patterns to apply the filter
        registrationBean.setOrder(1); // Set the order of the filter if needed

        return registrationBean;
    }
}