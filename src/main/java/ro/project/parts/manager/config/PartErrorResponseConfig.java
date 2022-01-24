package ro.project.parts.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.project.parts.manager.model.PartErrorResponse;

@Configuration
public class PartErrorResponseConfig {
    @Bean
    public PartErrorResponse partErrorResponse() {
        return new PartErrorResponse();
    }
}
