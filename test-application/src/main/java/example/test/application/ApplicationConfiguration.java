package example.test.application;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import test.api.DefaultIntegrationLocator;
import test.api.IntegrationCsvReportGenerator;
import test.api.IntegrationLoggingRunListener;
import test.api.runtime.ApplicationContextHolder;
import test.api.runtime.DefaultConfiguration;

/**
 * Imports the {@link DefaultConfiguration} to get all core integration components and explicitly exposes the
 * {@link DefaultIntegrationLocator} in this application to run all integration tests.
 */
@Configuration
@Import({DefaultConfiguration.class})
public class ApplicationConfiguration implements InitializingBean {
    private static final Logger logger = Logger.getLogger(ApplicationConfiguration.class);

    @Autowired
    private ApplicationContextHolder applicationContextHolder;

    @Value("${APPHOME}")
    private String appHome;

    @Override
    public void afterPropertiesSet() throws Exception {
        ApplicationContext applicationContext = ApplicationContextHolder.get();
        logger.info(String.format("Application Context found: %s, APPHOME: %s",
            applicationContext != null,
            appHome));
    }

    @Bean
    public IntegrationLoggingRunListener integrationLoggingRunListener() {
        return new IntegrationLoggingRunListener();
    }

    @Bean
    public IntegrationCsvReportGenerator integrationCsvReportGenerator() throws Exception {
        return new IntegrationCsvReportGenerator(appHome);
    }

    @Bean
    public DefaultIntegrationLocator integrationProcessor() {
        return new DefaultIntegrationLocator();
    }
}
