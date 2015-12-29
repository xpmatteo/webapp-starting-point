package it.xpug.todolist;

import it.xpug.toolkit.db.*;
import it.xpug.toolkit.html.*;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.saasovation.common.domain.model.*;

public class TodoListServlet extends HttpServlet {

	private DatabaseConfiguration configuration;

	public TodoListServlet(DatabaseConfiguration configuration) {
		this.configuration = configuration;
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Database database = new Database(configuration);
		response.setContentType("text/html");

		if (request.getMethod().equals("POST")) {
			TodoListsMainPageProjection projection = new TodoListsMainPageProjection(database);
			DomainEventPublisher.instance().reset();
			DomainEventPublisher.instance().subscribe(projection);
			new TodoListsController().onCreateNewList(request.getParameter("name"));
			response.sendRedirect("/");
			return;
		}

		TemplateView view = new TemplateView("index.ftl");
		view.put("todoLists", database.select("select * from todo_lists_main_page_projection").toCollection());
		PrintWriter writer = response.getWriter();
		writer.write(view.toHtml());
		writer.close();
	}
}
