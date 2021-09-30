package test.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A class-level annotation to indicate that the class is an integration test.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Integration {
    /**
     * The bean name of the "subject under test" to evaluate.
     *
     * @return
     */
    String bean() default "";

    /**
     * A short summary description of the integration test.
     *
     * @return
     */
    String description() default "Integration test";
}
