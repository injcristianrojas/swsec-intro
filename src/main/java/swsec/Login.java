package swsec;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends HttpServlet {
		private static final long serialVersionUID = 1L;
		private ServletContext servletcontext;

		public Login() {
			super();
		}

		public void init(ServletConfig config) throws ServletException {
			servletcontext = config.getServletContext();
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession(true);
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			try {
				Class.forName("org.sqlite.JDBC");
				Connection conexion = DriverManager.getConnection(Config.getSqliteUrl(servletcontext));
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
					session.setAttribute("username", username);
					response.sendRedirect("saludos.jsp");
				} else {
					response.sendRedirect("login.jsp?notFoundError=1");
				}
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

}
