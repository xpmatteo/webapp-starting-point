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

	InMemoryTodoListRepository repository = new InMemoryTodoListRepository();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Database database = new Database(configuration);
		response.setContentType("text/html");

		TodoListMainPageProjection projection = new TodoListMainPageProjection(database);

		DomainEventPublisher.instance().reset();
		DomainEventPublisher.instance().subscribe(projection);
		DomainEventPublisher.instance().subscribe(repository);

		WebRequest webRequest = new WebRequest(request);
		if (webRequest.matches("/todolists/?") && request.getMethod().equals("POST")) {
			new TodoListsController().onCreateNewList(request.getParameter("name"));
			response.sendRedirect("/");
			return;
		}

		if (webRequest.matches("/todolists/([-0-9a-fA-F]{36})") && request.getMethod().equals("POST")) {
			String id = webRequest.getUriParameter(1);
			String newName = request.getParameter("new_name");
			new TodoListsController().onRenameList(id, newName);
			response.sendRedirect(request.getRequestURI());
		}

		if (webRequest.matches("/todolists/([-0-9a-fA-F]{36})")) {
			TemplateView view = new TemplateView("todo_list.ftl");
			ListOfRows rows = database.select("select * from todo_lists_main_page_projection where id = ?", webRequest.getUriParameter(1));
			view.put("todoList", rows.get(0));
			PrintWriter writer = response.getWriter();
			writer.write(view.toHtml());
			writer.close();
		}

		TemplateView view = new TemplateView("index.ftl");
		view.put("todoLists", database.select("select * from todo_lists_main_page_projection").toCollection());
		PrintWriter writer = response.getWriter();
		writer.write(view.toHtml());
		writer.close();
	}
}
