package it.xpug.helloworld;

import static java.lang.String.format;
import it.xpug.toolkit.db.Database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet {
	private Database database;

	public HelloWorldServlet(Database database) {
		this.database = database;
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
		return database.selectOneValue("select 3+4");
	}
}
