package swsec;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.owasp.esapi.ESAPI;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Configuration config = null;
       
    public Login() {
        super();
        try {
			config  = new PropertiesConfiguration("app.properties");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexion = DriverManager.getConnection(config.getString("JDBC.connectionURL"));
			Statement statement = conexion.createStatement();
			String query = "select * from usuarios where username='" + username + "' and password = '" + password + "'";
			ResultSet resultado = statement.executeQuery(query);
			int numFilas = 0;
			while (resultado.next())
				numFilas++;
			statement.close();
			conexion.close();
			if (numFilas > 0) {
				session.setAttribute("userIP", request.getRemoteAddr());
				session.setAttribute("userHost", request.getRemoteHost());
				session.setAttribute("username", ESAPI.encoder().encodeForURL(username));
				response.sendRedirect("saludos.jsp");
			} else {
				response.sendRedirect("login.jsp?notFoundError=1");
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}