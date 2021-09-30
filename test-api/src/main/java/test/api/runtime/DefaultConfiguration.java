package test.api.runtime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * By default, expose the current Spring {@link org.springframework.context.ApplicationContext} if it exists.
 */
@Configuration
public class DefaultConfiguration {

    @Bean
    public ApplicationContextHolder applicationContextHolder() {
        return new ApplicationContextHolder();
    }

    @Bean
    public IntegrationContext integrationContext() {
        return new IntegrationContext();
    }
}
