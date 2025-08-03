package test.api.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import test.api.IntegrationResults;

/**
 * Listens for JUnit test results notified on the Spring Application Context.
 */
public class IntegrationContext implements ApplicationListener<IntegrationResults> {
    private static final Logger logger = LoggerFactory.getLogger(IntegrationContext.class);

    @Override
    public void onApplicationEvent(IntegrationResults event) {
        logger.info("Failure Count: {}, Ignored: {}, Total: {}",
            event.getResult().getFailureCount(),
            event.getResult().getIgnoreCount(),
            event.getResult().getRunCount());
    }
}
