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

public class Wall extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext servletContext;

	public Wall() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		servletContext = config.getServletContext();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		renderHeader(request, response);
		try {
			List<String> mensajes = Helpers.getMessages();
			PrintWriter writer = response.getWriter();
			for (String mensaje: mensajes)
				writer.println("<p>" + mensaje + "</p>");
			renderFooter(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mensaje = request.getParameter("mensaje");
		try {
			Helpers.insertPost(mensaje);
			doGet(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}


	private void renderHeader(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("X-XSS-Protection", "0");
		PrintWriter out = response.getWriter();
		RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/header.jsp");
		dispatcher.include(request, response);
		HttpSession session = request.getSession(true);
		if (session.getAttribute("username") != null) {
			out.println("<p>Usuario: " + session.getAttribute("username") + "</p>");
		}
		out.println("<form action='Wall' method='post'>");
		out.println("<input type='text' name='mensaje' id='mensaje' size='70'>");
		out.println("<input type='submit' value='Postear'>");
		out.println("</form>");
		out.println("<p><a href='index.jsp'>Volver</a></p>");
	}

	private void renderFooter(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/footer.inc");
		dispatcher.include(request, response);
	}

}
