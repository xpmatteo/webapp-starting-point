package it.xpug.todolist;

import it.xpug.toolkit.db.*;
import it.xpug.toolkit.html.*;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class HelloWorldServlet extends HttpServlet {

	private DatabaseConfiguration configuration;
	private List<TodoList> todoLists = Collections.synchronizedList(new ArrayList<>());

	public HelloWorldServlet(DatabaseConfiguration configuration) {
		this.configuration = configuration;
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TodoListRepository repository = new TodoListRepository(new Database(configuration));

		response.setContentType("text/html");

		if (request.getMethod().equals("POST")) {
			new TodoListsController(todoLists).onCreateNewList(request.getParameter("name"));
			response.sendRedirect("/");
			return;
		}

		TemplateView view = new TemplateView("index.ftl");
		view.put("todoLists", todoLists);
		PrintWriter writer = response.getWriter();
		writer.write(view.toHtml());
		writer.close();
	}
}
