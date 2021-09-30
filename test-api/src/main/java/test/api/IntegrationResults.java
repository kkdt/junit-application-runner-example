package test.api;

import org.junit.runner.Result;
import org.springframework.context.ApplicationEvent;

/**
 * A custom event wrapping the JUnit results.
 */
public class IntegrationResults extends ApplicationEvent {
    private final Result result;

    public IntegrationResults(Object source, Result result) {
        super(source);
        this.result = result;
    }

    /**
     * The test results.
     *
     * @return
     */
    public Result getResult() {
        return result;
    }
}
