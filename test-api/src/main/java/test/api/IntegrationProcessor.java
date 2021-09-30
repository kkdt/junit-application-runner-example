package test.api;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class IntegrationProcessor implements ApplicationContextAware, InitializingBean {
    private static final Logger logger = Logger.getLogger(IntegrationProcessor.class);

    private ApplicationContext applicationContext;
    private IntegrationRunner integrationRunner;

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
            logger.info(String.format("Found classes: %s, Beans: %s", classes, applicationContext.getBeansWithAnnotation(Integration.class).size()));

            integrationRunner = new IntegrationRunner(classes);
            integrationRunner.run();
        }
    }
}
