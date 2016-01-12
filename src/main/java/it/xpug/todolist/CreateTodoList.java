package it.xpug.todolist;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import com.saasovation.common.domain.model.*;

public class CreateTodoList extends Command {

	public void service(WebRequest webRequest, HttpServletResponse response) throws IOException {
	    service(webRequest, response, UUID.randomUUID().toString());
    }

	void service(WebRequest webRequest, HttpServletResponse response, String id) throws IOException {
		DomainEventPublisher.instance().publish(new TodoListCreatedEvent(id, webRequest.getParameter("name")));
	    response.sendRedirect("/");
    }

}
