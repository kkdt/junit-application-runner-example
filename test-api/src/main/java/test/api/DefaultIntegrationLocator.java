package test.api;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Locates all {@link Integration} beans within an Spring application and execute all the JUnit tests. The default behavior
 * will immediately locate and execute once this bean is hooked into the Spring Application Context.
 *
 */
public class DefaultIntegrationLocator implements ApplicationContextAware, InitializingBean {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultIntegrationLocator.class);

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(applicationContext != null) {
            List<Class<?>> classes = applicationContext.getBeansWithAnnotation(Integration.class).values().stream()
                .map(Object::getClass)
                .collect(Collectors.toList());
            logger.info("Found @Integration classes: {}, Count: {}", classes, classes.size());

            JUnitCore junit = new JUnitCore();
            // find all RunListener within the Spring Application Context and attach them to the tests
            applicationContext.getBeansOfType(RunListener.class)
                .values()
                .forEach(junit::addListener);
            Result results = junit.run(classes.toArray(new Class<?>[0]));
            // publish test results when all tests are executed
            applicationContext.publishEvent(new IntegrationResults(this, results));
        }
    }
}
