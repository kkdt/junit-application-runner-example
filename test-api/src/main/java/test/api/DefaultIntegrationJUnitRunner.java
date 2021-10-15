package test.api;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import test.api.runtime.ApplicationContextHolder;

/**
 * Extends the default JUnit runner to take advantage of its handling of various test cycles and annotations.
 */
public class DefaultIntegrationJUnitRunner extends BlockJUnit4ClassRunner {

    private final Integration annotation;

    /**
     * Evaluate any {@link Integration} annotations from the class.
     *
     * @param c
     * @throws InitializationError
     */
    public DefaultIntegrationJUnitRunner(Class<?> c) throws InitializationError {
        super(c);
        this.annotation = c.getAnnotation(Integration.class);
    }

    /**
     * This will return the underlying bean within the Application Context if set; otherwise, default behaviors.
     *
     * @return
     * @throws Exception
     */
    @Override
    protected Object createTest() throws Exception {
        if(annotation != null && ApplicationContextHolder.get() != null) {
            return ApplicationContextHolder.get().getBean(annotation.bean());
        }
        return super.createTest();
    }
}
