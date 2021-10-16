package test.api.runtime;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import test.api.IntegrationResults;

/**
 * Listens for JUnit test results notified on the Spring Application Context.
 */
public class IntegrationContext implements ApplicationListener<IntegrationResults> {
    private static final Logger logger = Logger.getLogger(IntegrationContext.class);

    @Override
    public void onApplicationEvent(IntegrationResults event) {
        IntegrationResults results = event;
        logger.info(String.format("Failure Count: %s, Ignored: %s, Total: %s",
            event.getResult().getFailureCount(),
            event.getResult().getIgnoreCount(),
            event.getResult().getRunCount()));
    }
}
