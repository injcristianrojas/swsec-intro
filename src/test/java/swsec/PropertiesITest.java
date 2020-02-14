package swsec;

import org.junit.Test;
import org.junit.Assert;
import swsec.config.ApplicationProperties;

public class PropertiesITest {

    @Test
    public void propertiesTest() {
        if (ApplicationProperties.INSTANCE.getProperties().contains("true")) {
            Assert.assertEquals(true, ApplicationProperties.INSTANCE.usesJWT());
        } else {
            Assert.assertEquals(false, ApplicationProperties.INSTANCE.usesJWT());
        }
    }

}