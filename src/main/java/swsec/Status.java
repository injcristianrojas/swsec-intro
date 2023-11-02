package swsec;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Status extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext sc;

	final String filePath = "log.log";

	public Status() {
		super();
	}

	public void init(ServletConfig config) {
		sc = config.getServletContext();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> last10lines = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			Deque<String> deque = new LinkedList<>();
			while ((line = reader.readLine()) != null) {
				if (deque.size() >= 10) {
					deque.poll();
				}
				deque.offer(line);
			}
			last10lines.addAll(deque);
		}

		PrintWriter out = response.getWriter();
		RequestDispatcher dispatcher = sc.getRequestDispatcher("/header.jsp");
		dispatcher.include(request, response);
		out.println("Latest 10 status entries:<br>");

		out.println("<pre>");
		for (String line : last10lines) {
			out.println(line);
		}
		out.println("</pre>");

		dispatcher = sc.getRequestDispatcher("/footer.inc");
		dispatcher.include(request, response);
	}
}
