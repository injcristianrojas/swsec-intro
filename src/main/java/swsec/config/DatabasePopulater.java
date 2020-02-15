package swsec.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import swsec.Helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabasePopulater implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "JWT is " + (ApplicationProperties.INSTANCE.usesJWT() ? "enabled" : "disabled"));
        populateDatabase();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    private void populateDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            String messageTable = "CREATE TABLE IF NOT EXISTS 'mensajes' ('id' INTEGER PRIMARY KEY NOT NULL, 'mensaje' TEXT NOT NULL );";
            String userTable = "CREATE TABLE IF NOT EXISTS 'usuarios' ('id' INTEGER PRIMARY KEY NOT NULL, 'username' VARCHAR(10) NOT NULL, 'password' VARCHAR(10) NOT NULL ,'type' INTEGER);";
            Connection conn = DriverManager.getConnection(Helpers.SQLITE_URL);
            Statement stmt = conn.createStatement();
            stmt.setQueryTimeout(30);
            stmt.executeUpdate(userTable);
            stmt.executeUpdate(messageTable);
            stmt.executeUpdate("DELETE FROM usuarios");
            stmt.executeUpdate("DELETE FROM mensajes");
            stmt.executeUpdate("INSERT INTO usuarios(username, password, type) VALUES ('admin', 'admin', '1')");
            stmt.executeUpdate("INSERT INTO usuarios(username, password, type) VALUES ('jperez', '123', '2')");
            stmt.executeUpdate("INSERT INTO usuarios(username, password, type) VALUES ('basaber', '12345', '2')");
            stmt.executeUpdate("INSERT INTO usuarios(username, password, type) VALUES ('skramer', 'power', '2')");
            stmt.executeUpdate("INSERT INTO usuarios(username, password, type) VALUES ('aeinstein', 'simple', '2')");
            stmt.executeUpdate("INSERT INTO usuarios(username, password, type) VALUES ('tstark', 'ironman', '2')");
            stmt.executeUpdate("INSERT INTO usuarios(username, password, type) VALUES ('cpalma', 'lepego', '2')");
            stmt.executeUpdate("INSERT INTO usuarios(username, password, type) VALUES ('asavage', 'boom', '2')");
            stmt.executeUpdate("INSERT INTO usuarios(username, password, type) VALUES ('jhyneman', 'boom', '2')");
            stmt.executeUpdate("INSERT INTO usuarios(username, password, type) VALUES ('tanderson', 'matrix', '2')");
            stmt.executeUpdate("INSERT INTO usuarios(username, password, type) VALUES ('zcool', 'god', '2')");
            stmt.executeUpdate("INSERT into mensajes(mensaje) VALUES ('Bienvenidos al foro de Fans de las Aves Chilenas. Soy el Administrador.')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
