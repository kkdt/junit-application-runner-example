package test.api;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handle callbacks from JUnit by logging it out to Log4J.
 */
public class IntegrationLoggingRunListener extends RunListener {
    private static final Logger logger = LoggerFactory.getLogger(IntegrationLoggingRunListener.class);

    @Override
    public void testFailure(Failure failure) throws Exception {
        Description description = failure.getDescription();
        String method = description.getMethodName();
        String className = description.getClassName();
        String message = failure.getMessage();
        logger.error("Test failed, Class: {}, Method: {}, Message: {}, Header: {}",
            className,  method, message, failure.getTestHeader());
    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        Description description = failure.getDescription();
        String method = description.getMethodName();
        String className = description.getClassName();
        String message = failure.getMessage();
        String trace = failure.getTrace();
        logger.error("Test assumption failed, Class: {}, Method: {}, Message: {}\n{}",
            className,  method, message, trace);
    }

    @Override
    public void testFinished(Description description) throws Exception {
        String method = description.getMethodName();
        String className = description.getClassName();
        String displayName = description.getDisplayName();
        Integration integration = description.getTestClass().getAnnotation(Integration.class);
        logger.info("Test finished, Class: {}, Method: {}, DisplayName: {}, Integration: {}",
            className,  method, displayName, integration != null);
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        logger.info("Test Run took {} (ms), Successful: {}", result.getRunTime(), result.wasSuccessful());
    }

    @Override
    public void testRunStarted(Description description) throws Exception {
        String method = description.getMethodName();
        String className = description.getClassName();
        String displayName = description.getDisplayName();
        logger.info("Test Run Started, Class: {}, Method: {}, Name: {}",
            className, method, displayName);
    }

    @Override
    public void testStarted(Description description) throws Exception {
        String method = description.getMethodName();
        String className = description.getClassName();
        String displayName = description.getDisplayName();
        Integration integration = description.getTestClass().getAnnotation(Integration.class);
        logger.info("Test Started, Class: {}, Method: {}, Name: {}, Integration: {}",
            className, method, displayName, integration != null);
    }

    @Override
    public void testIgnored(Description description) throws Exception {
        String method = description.getMethodName();
        String className = description.getClassName();
        String displayName = description.getDisplayName();
        Integration integration = description.getTestClass().getAnnotation(Integration.class);
        logger.info("Test Ignored, Class: {}, Method: {}, Name: {}, Integration: {}",
            className, method, displayName, integration != null);
    }
}
