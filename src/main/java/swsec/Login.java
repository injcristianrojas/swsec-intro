package swsec;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static swsec.Helpers.checkUserLogin;

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
		if (checkUserLogin(username, password)) {
			session.setAttribute("username", username);
			String landingPage = request.getParameter("landingPage");
			response.sendRedirect(landingPage != null ? landingPage : "saludos.jsp");
		} else {
			response.sendRedirect("login.jsp?notFoundError=1");
		}
	}

}
