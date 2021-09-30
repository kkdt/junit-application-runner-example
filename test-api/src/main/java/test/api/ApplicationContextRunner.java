package test.api;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
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
        for (Method method : testSubject.getClass().getMethods()) {
            String methodName = method.getName();
            String className = testSubject.getClass().getName();
            Description description = Description.createTestDescription(c, methodName);
            if (method.isAnnotationPresent(Test.class)) {
                notifier.fireTestStarted(description);
                try {
                    method.invoke(testSubject);
                } catch (Exception e) {
                    Failure failure = new Failure(description, e);
                    notifier.fireTestFailure(failure);
                }
                notifier.fireTestFinished(description);
            }
        }
    }

    protected Object getTestSubject() {
        if(annotation != null && ApplicationContextHolder.get() != null) {
            return ApplicationContextHolder.get().getBean(annotation.bean());
        }
        return c;
    }
}
