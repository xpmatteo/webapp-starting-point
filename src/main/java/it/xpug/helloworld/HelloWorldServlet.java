package it.xpug.helloworld;

import static java.lang.String.format;
import it.xpug.toolkit.db.Database;
import it.xpug.toolkit.db.DatabaseConfiguration;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class HelloWorldServlet extends HttpServlet {
	private DatabaseConfiguration configuration;

	public HelloWorldServlet(DatabaseConfiguration configuration) {
		this.configuration = configuration;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write(format("<p id='message'>Hello, world: %s</p>", seven()));
		writer.close();
	}

	private Object seven() {
		Database database = new Database(configuration);
		return database.selectOneValue("select 3+4");
	}
}
