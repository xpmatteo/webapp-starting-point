package it.xpug.todolist;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import com.saasovation.common.domain.model.*;

public class CreateTodoItem extends Command {

	public void service(WebRequest webRequest, HttpServletResponse response) throws IOException {
		service(webRequest, response, UUID.randomUUID().toString());
    }

	void service(WebRequest webRequest, HttpServletResponse response, String todoItemId) throws IOException {
	    String id = webRequest.getPathParameter(1);
	    String newItem = webRequest.getParameter("new_item");
		DomainEventPublisher.instance().publish(new TodoItemCreatedEvent(todoItemId, id, newItem));
	    response.sendRedirect(webRequest.getPath());
    }

}
