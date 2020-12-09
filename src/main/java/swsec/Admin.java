package swsec;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext servletContext;

	public Admin() {
		super();
	}

	public void init(ServletConfig config) {
		servletContext = config.getServletContext();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			renderHeader(request, response);
			if (request.getParameter("delete") != null) {
				Helpers.deleteUser(request.getParameter("delete"));
			}
			List<String> usuarios = Helpers.getUsers("2");
			PrintWriter writer = response.getWriter();
			writer.println("<table border='1'>");
			writer.println("<tr><td colspan='2'><b>Usuarios del sistema</b></td></tr>");
			for (String usuario: usuarios)
				writer.println("<tr><td>" + usuario + "</td><td><a id='delete_" + usuario +  "' href='Admin?delete=" + usuario + "'>&nbsp;Borrar&nbsp;</a></td></tr>");
			writer.println("</table>");
			renderFooter(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			Helpers.insertUser(username, password);
			doGet(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}


	private void renderHeader(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/header.jsp");
		dispatcher.include(request, response);
		HttpSession session = request.getSession(true);
		if (session.getAttribute("username") != null) {
			out.println("<p>Usuario: " + session.getAttribute("username") + "</p>");
		}
		out.println("<form action='Admin' method='post'>");
		out.println("Nombre de usuario: <input type='text' name='username' id='username' size='20'> ");
		out.println("Password: <input type='text' name='password' id='password' type='password' size='20'>");
		out.println("<input type='submit' value='A&ntilde;adir'>");
		out.println("</form>");
		out.println("<p />");
	}

	private void renderFooter(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/footer.inc");
		dispatcher.include(request, response);
	}

}
