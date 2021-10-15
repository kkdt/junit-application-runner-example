package example.test.application;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import test.api.DefaultIntegrationJUnitRunner;
import test.api.Integration;

/**
 * Test the "--number" option from runscript.
 */
@RunWith(DefaultIntegrationJUnitRunner.class)
@Integration(
    bean = "applicationTestNumberProperty",
    description = "Test the \"--number\" option from runscript"
)
@Component
public class ApplicationTestNumberProperty {
    @Value("${test.application.applicationNumber:-1}")
    private Integer applicationNumber;

    @Test
    public void testApplicationNumberNotNull() {
        Assert.assertNotNull("The 'applicationNumber' variable cannot be null", applicationNumber);
    }

    @Test
    public void testApplicationNumber() {
        Assert.assertTrue(String.format("Expecting a value greater than -1 but received %s", applicationNumber), applicationNumber > -1);
    }
}
