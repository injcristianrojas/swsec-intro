package swsec;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ListaUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext servletContext;

	public void init(ServletConfig config) {
		servletContext = config.getServletContext();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			renderHeader(request, response);
			List<String> usuarios = Helpers.getUsers(request.getParameter("type"));
			PrintWriter writer = response.getWriter();
			writer.println("<table border='1'>");
			writer.println("<tr><td><b>Usuarios del sistema</b></td></tr>");
			for (String usuario: usuarios)
				writer.println("<tr><td>" + usuario + "</td></tr>");
			writer.println("</table>");
			renderFooter(request, response);
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
	}

	private void renderFooter(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/footer.inc");
		dispatcher.include(request, response);
	}

}
