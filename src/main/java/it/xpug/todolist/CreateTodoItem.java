package it.xpug.todolist;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import com.saasovation.common.domain.model.*;

public class CreateTodoItem {

	private WebRequest webRequest;
	private HttpServletResponse response;

	public CreateTodoItem(WebRequest webRequest, HttpServletResponse response) {
		this.webRequest = webRequest;
		this.response = response;
    }

	public void service() throws IOException {
		service(UUID.randomUUID().toString());
    }

	void service(String todoItemId) throws IOException {
	    String id = webRequest.getPathParameter(1);
	    String newItem = webRequest.getParameter("new_item");
		DomainEventPublisher.instance().publish(new TodoItemCreatedEvent(todoItemId, id, newItem));
	    response.sendRedirect(webRequest.getPath());
    }

}
