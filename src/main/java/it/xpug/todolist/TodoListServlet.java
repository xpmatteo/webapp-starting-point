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
		EventStoreTodoListRepository repository = new EventStoreTodoListRepository(database);
		TodoItemRepository todoItems = new TodoItemRepository(database);
		DomainEventPublisher.instance().reset();
		DomainEventPublisher.instance().subscribe(projection);
		DomainEventPublisher.instance().subscribe(repository);

		WebRequest webRequest = new WebRequest(request);
		if (webRequest.matches("/todoitems/" + ID_REGEX) && request.getMethod().equals("POST")) {
			new CheckTodoItemController(webRequest, response, todoItems).service();
			return;
		}

		if (webRequest.matches("/todolists/?") && request.getMethod().equals("POST")) {
			createTodoList(webRequest, response);
			return;
		}

		if (webRequest.matches("/todolists/" + ID_REGEX)
				&& webRequest.isPost()
				&& webRequest.hasParameter("new_name")) {
			renameList(webRequest, response);
			return;
		}

		if (webRequest.matches("/todolists/" + ID_REGEX)
				&& webRequest.isPost()
				&& webRequest.hasParameter("new_item")) {
			createTodoItem(webRequest, response);
			return;
		}

		if (webRequest.matches("/todolists/" + ID_REGEX)) {
			showSingleList(database, webRequest, response);
			return;
		}

		if (webRequest.matches("/")) {
			showAllLists(database, response);
			return;
		}

		showNotFound(response);
	}

	private void createTodoItem(WebRequest webRequest, HttpServletResponse response) throws IOException {
	    String id = webRequest.getUriParameter(1);
	    String newItem = webRequest.getParameter("new_item");
	    new TodoListsController().onAddTodoItem(id, newItem);
	    response.sendRedirect(webRequest.getPath());
    }

	private void createTodoList(WebRequest webRequest, HttpServletResponse response) throws IOException {
	    new TodoListsController().onCreateNewList(webRequest.getParameter("name"));
	    response.sendRedirect("/");
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

	private void showSingleList(Database database, WebRequest webRequest, HttpServletResponse response)
            throws IOException {
	    String todoListId = webRequest.getUriParameter(1);
		ListOfRows todoLists = database.select("select * from todo_lists_main_page_projection where id = ?", todoListId);
		ListOfRows todoItems = database.select("select * from todo_items_page_projection where todo_list_id = ?", todoListId);
	    TemplateView view = new TemplateView("todo_list.ftl");
	    view.put("todoList", todoLists.get(0));
	    view.put("todoItems", todoItems.toCollection());
	    PrintWriter writer = response.getWriter();
	    writer.write(view.toHtml());
	    writer.close();
    }

	private void renameList(WebRequest webRequest, HttpServletResponse response) throws IOException {
	    String id = webRequest.getUriParameter(1);
	    String newName = webRequest.getParameter("new_name");
	    new TodoListsController().onRenameList(id, newName);
	    response.sendRedirect(webRequest.getPath());
    }
}
