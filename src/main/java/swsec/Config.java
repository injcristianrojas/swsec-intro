package swsec;

import javax.servlet.ServletContext;

public class Config {

    public static final String getSqliteUrl(ServletContext servletContext) {
      return "jdbc:sqlite:" + servletContext.getRealPath("WEB-INF/swsecdemo.sqlite");
    }

}
