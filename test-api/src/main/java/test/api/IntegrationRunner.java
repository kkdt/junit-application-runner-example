package test.api;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.junit.runner.JUnitCore;

public class IntegrationRunner {
    private static final Logger logger = Logger.getLogger(IntegrationRunner.class);

    private final List<Class<?>> classes;

    public IntegrationRunner() {
        this.classes = new ArrayList<>();
    }

    public IntegrationRunner(List<Class<?>> classes) {
        this.classes = classes;
    }

    public void run() {
        logger.info(String.format("Number of integration tests: %s", classes.size()));
        JUnitCore junit = new JUnitCore();
        junit.addListener(new IntegrationRecorder());
        junit.run(classes.toArray(new Class<?>[0]));
    }
}
