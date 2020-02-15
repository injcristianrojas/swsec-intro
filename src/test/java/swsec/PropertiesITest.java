package swsec;

import org.junit.Test;
import org.junit.Assert;
import swsec.config.ApplicationProperties;

public class PropertiesITest {

    @Test
    public void propertiesTest() {
        if (ApplicationProperties.INSTANCE.getProperties().contains("true")) {
            Assert.assertTrue(ApplicationProperties.INSTANCE.usesJWT());
        } else {
            Assert.assertFalse(ApplicationProperties.INSTANCE.usesJWT());
        }
    }

}