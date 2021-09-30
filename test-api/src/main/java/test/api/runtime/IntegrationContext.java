package test.api.runtime;

import org.apache.log4j.Logger;
import org.junit.runner.Result;
import org.springframework.context.ApplicationListener;
import test.api.IntegrationResults;

/**
 * Listens for JUnit test results notified on the Spring Application Context.
 */
public class IntegrationContext implements ApplicationListener<IntegrationResults> {
    private static final Logger logger = Logger.getLogger(IntegrationContext.class);

    @Override
    public void onApplicationEvent(IntegrationResults event) {
        Result result = event.getResult();
        logger.info(String.format("Failure Count: %s, Ignored: %s, Total: %s", result.getFailureCount(), result.getIgnoreCount(), result.getRunCount()));
    }
}
