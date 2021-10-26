package test.api;

import org.apache.log4j.Logger;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

/**
 * Handle callbacks from JUnit by logging it out to Log4J.
 */
public class IntegrationLoggingRunListener extends RunListener {
    private static final Logger logger = Logger.getLogger(IntegrationLoggingRunListener.class);

    @Override
    public void testFailure(Failure failure) throws Exception {
        Description description = failure.getDescription();
        String method = description.getMethodName();
        String className = description.getClassName();
        String message = failure.getMessage();
        String trace = failure.getTrace();
        logger.error(String.format("Test failed, Class: %s, Method: %s, Message: %s, Header: %s", className,  method, message, failure.getTestHeader()));
    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        Description description = failure.getDescription();
        String method = description.getMethodName();
        String className = description.getClassName();
        String message = failure.getMessage();
        String trace = failure.getTrace();
        logger.error(String.format("Test assumption failed, Class: %s, Method: %s, Message: %s\n%s", className,  method, message, trace));
    }

    @Override
    public void testFinished(Description description) throws Exception {
        String method = description.getMethodName();
        String className = description.getClassName();
        String displayName = description.getDisplayName();
        Integration integration = description.getTestClass().getAnnotation(Integration.class);
        logger.info(String.format("Test finished, Class: %s, Method: %s, Name: %s, Integration: %s",
            className,
            method,
            displayName,
            integration != null));
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        logger.info(String.format("Test Run took %s (ms), Successful: %s", result.getRunTime(), result.wasSuccessful()));
    }

    @Override
    public void testRunStarted(Description description) throws Exception {
        String method = description.getMethodName();
        String className = description.getClassName();
        String displayName = description.getDisplayName();
        logger.info(String.format("Test Run Started, Class: %s, Method: %s, Name: %s", className, method, displayName));
    }

    @Override
    public void testStarted(Description description) throws Exception {
        String method = description.getMethodName();
        String className = description.getClassName();
        String displayName = description.getDisplayName();
        Integration integration = description.getTestClass().getAnnotation(Integration.class);
        logger.info(String.format("Test Started, Class: %s, Method: %s, Name: %s, Integration: %s",
            className,
            method,
            displayName,
            integration != null));
    }

    @Override
    public void testIgnored(Description description) throws Exception {
        String method = description.getMethodName();
        String className = description.getClassName();
        String displayName = description.getDisplayName();
        Integration integration = description.getTestClass().getAnnotation(Integration.class);
        logger.info(String.format("Test Ignored, Class: %s, Method: %s, Name: %s, Integration: %s",
            className,
            method,
            displayName,
            integration != null));
    }
}
