package swsec.config;

import java.io.IOException;
import java.io.FileInputStream;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public enum ApplicationProperties {
  INSTANCE;

  private final Properties properties;

  ApplicationProperties() {
    properties = new Properties();
    try {
      properties.load(new FileInputStream("src/main/resources/app.properties"));
    } catch (IOException e) {
      Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
    }
  }

  public String getProperties() {
    return properties.toString();
  }

  public boolean usesJWT() {
    return properties.getProperty("jwt.enabled").equals("true");
  }

  public String testUser() {
    return properties.getProperty("test.default_user");
  }

  public String testPassword() {
    return properties.getProperty("test.default_password");
  }
}