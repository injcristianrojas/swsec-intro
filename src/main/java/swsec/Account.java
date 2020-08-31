package swsec;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Account extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext servletContext;

	public Account() {
		super();
	}

	public void init(ServletConfig config) {
		servletContext = config.getServletContext();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		HttpSession session = request.getSession(true);
		String password = request.getParameter("password");
		try {
			renderHeader(request, response);
			String username = (String) session.getAttribute("username");
			int updatedRows = Helpers.getUpdatedRows(username, password);
			PrintWriter writer = response.getWriter();
			writer.println(updatedRows > 0 ? "Password cambiada exitosamente."
					: "Error al intentar cambiar la password<br /><a href='cuenta.jsp'>Volver</a>");
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