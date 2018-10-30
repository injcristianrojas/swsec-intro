package swsec;

import java.sql.*;

public class Helpers {

    public static boolean checkUserLogin(String username, String password) {
        int numFilas = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection(getSqliteUrl());
            Statement statement = conexion.createStatement();
            String query = "select * from usuarios where username = '" + username + "' and password = '" + password + "'";
            ResultSet resultado = statement.executeQuery(query);
            while (resultado.next())
                numFilas++;
            statement.close();
            conexion.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numFilas > 0;
    }

    public static final String getSqliteUrl() {
        return "jdbc:sqlite:swsecdemo.sqlite";
    }
}
