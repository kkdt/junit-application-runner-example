package test.api;

import org.apache.log4j.Logger;
import org.junit.runner.Description;
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
        logger.error(String.format("Class: %s, Method: %s, Message: %s\n%s", className,  method, message, trace));
    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        Description description = failure.getDescription();
        String method = description.getMethodName();
        String className = description.getClassName();
        String message = failure.getMessage();
        String trace = failure.getTrace();
        logger.error(String.format("Class: %s, Method: %s, Message: %s\n%s", className,  method, message, trace));
    }
}
