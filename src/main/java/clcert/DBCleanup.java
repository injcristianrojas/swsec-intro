package clcert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class DBCleanup {

	public static void main(String[] args) {
		try {
			PropertiesConfiguration config = new PropertiesConfiguration("app.properties");
			Class.forName("org.sqlite.JDBC");
			Connection conexion = DriverManager.getConnection(config.getString("JDBC.connectionURL"));
			Statement statement = conexion.createStatement();
			int rowcount = statement.executeUpdate("delete from mensajes");
			statement.close();
			conexion.close();
			System.out.println("Filas borradas: " + rowcount);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
