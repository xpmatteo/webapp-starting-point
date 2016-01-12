package it.xpug.todolist;

import it.xpug.toolkit.db.*;
import it.xpug.toolkit.html.*;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.saasovation.common.domain.model.*;

public class TodoListServlet extends HttpServlet {

	private static final String ID_REGEX = "([-0-9a-fA-F]{36})";

	private ConnectionFactory configuration;

	public TodoListServlet(ConnectionFactory configuration) {
		this.configuration = configuration;
    }


	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		Database database = new Database(configuration);

		TodoListMainPageProjection projection = new TodoListMainPageProjection(database);
		EventStore eventStore = new EventStore(database);
		TodoItemRepository todoItems = new TodoItemRepository(eventStore);
		//TodoListRepository todoLists = new TodoListRepository(database);
		DomainEventPublisher.instance().reset();
		DomainEventPublisher.instance().subscribe(projection);
		DomainEventPublisher.instance().subscribe(eventStore);

		WebRequest webRequest = new WebRequest(request);
		System.out.println(webRequest);
		if (webRequest.matches("/todoitems/" + ID_REGEX) && webRequest.isPost()) {
			new CheckTodoItem(webRequest, response, todoItems).service();
			return;
		}

		if (webRequest.matches("/todolists/?") && webRequest.isPost()) {
			new CreateTodoList(webRequest, response).service();
			return;
		}

		if (webRequest.matches("/todolists/" + ID_REGEX) && webRequest.isPost() && webRequest.hasParameter("new_name")) {
			new RenameTodoList(webRequest, response).service();
			return;
		}

		if (webRequest.matches("/todolists/" + ID_REGEX) && webRequest.isPost() && webRequest.hasParameter("new_item")) {
			new CreateTodoItem(webRequest, response).service();
			return;
		}

		if (webRequest.matches("/todolists/" + ID_REGEX)) {
			new ShowSingleList(webRequest, response, database).service();
			return;
		}

		if (webRequest.matches("/")) {
			showAllLists(database, response);
			return;
		}

		showNotFound(response);
	}

	private void showNotFound(HttpServletResponse response) throws IOException {
		response.setStatus(404);
		response.getWriter().write("Not found");
    }

	private void showAllLists(Database database, HttpServletResponse response) throws IOException {
	    TemplateView view = new TemplateView("index.ftl");
		view.put("todoLists", database.select("select * from todo_lists_main_page_projection").toCollection());
		PrintWriter writer = response.getWriter();
		writer.write(view.toHtml());
		writer.close();
    }
}
