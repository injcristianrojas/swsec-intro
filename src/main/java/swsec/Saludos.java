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

public class Saludos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext sc;

	public Saludos() {
		super();
	}

	public void init(ServletConfig config) {
		sc = config.getServletContext();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("X-XSS-Protection", "0");
		PrintWriter out = response.getWriter();
		RequestDispatcher dispatcher = sc.getRequestDispatcher("/header.jsp");
		dispatcher.include(request, response);
		out.println("Bienvenido al sistema, " + request.getParameter("nombre"));
		dispatcher = sc.getRequestDispatcher("/footer.inc");
		dispatcher.include(request, response);
	}
}
