package example.test.application;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import test.api.ApplicationContextRunner;
import test.api.Integration;

@RunWith(ApplicationContextRunner.class)
@Integration(
    bean = "applicationTestEvaluateProperty"
)
@Component
public class ApplicationTestEvaluateProperty {

    @Value("${test.application.evaluate:false}")
    private Boolean evaluate;

    @Test
    public void testEvaluateNotNull() {
        Assert.assertNotNull("The 'evaluate' variable cannot be null", evaluate);
    }

    @Test
    public void testEvaluate() {
        Assert.assertTrue(evaluate);
    }
}
