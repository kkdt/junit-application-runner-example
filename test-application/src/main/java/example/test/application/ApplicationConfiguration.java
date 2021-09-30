package example.test.application;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import test.api.IntegrationProcessor;
import test.api.runtime.ApplicationContextHolder;

@Configuration
public class ApplicationConfiguration implements InitializingBean {
    private static final Logger logger = Logger.getLogger(ApplicationConfiguration.class);

    @Autowired
    private ApplicationContextHolder applicationContextHolder;

    @Override
    public void afterPropertiesSet() throws Exception {
        ApplicationContext applicationContext = ApplicationContextHolder.get();
        logger.info(String.format("Application Context found: %s", applicationContext != null));
    }

    @Bean
    public IntegrationProcessor integrationProcessor() {
        return new IntegrationProcessor();
    }
}
