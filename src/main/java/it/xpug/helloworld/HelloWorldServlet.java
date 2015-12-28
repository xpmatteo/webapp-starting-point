package it.xpug.helloworld;

import it.xpug.toolkit.db.*;
import it.xpug.toolkit.html.*;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class HelloWorldServlet extends HttpServlet {

	public HelloWorldServlet(DatabaseConfiguration configuration) {
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		if (request.getMethod().equals("POST")) {
			List<TodoList> todoLists = new ArrayList<>();
			new TodoListsController(todoLists).onCreateNewList(request.getParameter("name"));
			response.sendRedirect("/");
			return;
		}

		TemplateView view = new TemplateView("index.ftl");
		PrintWriter writer = response.getWriter();
		writer.write(view.toHtml());
		writer.close();
	}
}
