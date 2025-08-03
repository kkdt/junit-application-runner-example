package test.api.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Spring-aware object that exposes the {@link ApplicationContext} for programmatic access. This must be set as a bean
 * within a Spring application; otherwise, null will be returned.
 */
public class ApplicationContextHolder implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextHolder.class);
    private static ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    /**
     * The current Spring Application Context.
     *
     * @return the context or null if none exist.
     */
    public static ApplicationContext get() {
        return ctx;
    }

    public static void close() {
        logger.info("Closing application context");
        ((ConfigurableApplicationContext)ctx).close();
    }

    public static void stop() {
        logger.info("Stopping application context");
        ((ConfigurableApplicationContext)ctx).stop();
    }
}
