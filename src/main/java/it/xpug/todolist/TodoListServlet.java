package it.xpug.todolist;

import it.xpug.toolkit.db.*;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.saasovation.common.domain.model.*;

public class TodoListServlet extends HttpServlet {

	private ConnectionFactory configuration;

	public TodoListServlet(ConnectionFactory connectionFactory) {
		this.configuration = connectionFactory;
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		Database database = new Database(configuration);
		TodoListMainPageProjection projection = new TodoListMainPageProjection(database);
		EventStore eventStore = new EventStore(database);
		DomainEventPublisher.instance().reset();
		DomainEventPublisher.instance().subscribe(projection);
		DomainEventPublisher.instance().subscribe(eventStore);

		WebRequest webRequest = new WebRequest(request);
		System.out.println(webRequest);

		Router router = new TodoListRouter(database, eventStore);
		router.getCommandFor(webRequest).service(webRequest, response);
	}

}
