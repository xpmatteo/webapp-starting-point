package it.xpug.todolist;

import it.xpug.toolkit.db.*;

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

		Router router = new Router();

		router.onPost("/todoitems/" + ID_REGEX, () -> new CheckTodoItem(todoItems));
		if (webRequest.matches("/todoitems/" + ID_REGEX) && webRequest.isPost()) {
			new CheckTodoItem(todoItems).service(webRequest, response);
			return;
		}

		router.onPost("/todolists/?", () -> new CreateTodoList());
		if (webRequest.matches("/todolists/?") && webRequest.isPost()) {
			new CreateTodoList().service(webRequest, response);
			return;
		}

		router.onPost("/todolists/" + ID_REGEX, () -> new RenameTodoList()).withNonEmptyParameter("new_name");
		if (webRequest.matches("/todolists/" + ID_REGEX) && webRequest.isPost() && webRequest.hasParameter("new_name")) {
			new RenameTodoList().service(webRequest, response);
			return;
		}

		router.onPost("/todolists/" + ID_REGEX, () -> new CreateTodoItem()).withNonEmptyParameter("new_item");
		if (webRequest.matches("/todolists/" + ID_REGEX) && webRequest.isPost() && webRequest.hasParameter("new_item")) {
			new CreateTodoItem().service(webRequest, response);
			return;
		}

		//router.onGet("/todolists/" + ID_REGEX, () -> new ShowSingleList(database));
		if (webRequest.matches("/todolists/" + ID_REGEX)) {
			new ShowSingleList(database).service(webRequest, response);
			return;
		}

		if (webRequest.matches("/")) {
			new ShowAllLists(response, database).service();
			return;
		}

		new ShowNotFound(response).service();
		//router.getCommandFor(webRequest).service(webRequest, response);

	}

}
