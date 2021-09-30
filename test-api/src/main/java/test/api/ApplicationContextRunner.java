package test.api;

import java.lang.reflect.Method;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import test.api.runtime.ApplicationContextHolder;

public class ApplicationContextRunner extends Runner {
    private final Class<?> c;
    private Integration annotation;

    public ApplicationContextRunner(Class<?> c) {
        this.c = c;
        this.annotation = c.getAnnotation(Integration.class);
    }

    @Override
    public Description getDescription() {
        return Description.createTestDescription(c, annotation != null ? annotation.description() : "No Description");
    }

    @Override
    public void run(RunNotifier notifier) {
        Object testSubject = getTestSubject();
        try {
            for (Method method : testSubject.getClass().getMethods()) {
                if (method.isAnnotationPresent(Test.class)) {
                    notifier.fireTestStarted(Description.createTestDescription(c, method.getName()));
                    method.invoke(testSubject);
                    notifier.fireTestFinished(Description.createTestDescription(c, method.getName()));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected Object getTestSubject() {
        if(annotation != null && ApplicationContextHolder.get() != null) {
            return ApplicationContextHolder.get().getBean(annotation.bean());
        }
        return c;
    }
}
