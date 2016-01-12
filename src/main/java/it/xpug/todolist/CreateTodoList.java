package it.xpug.todolist;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import com.saasovation.common.domain.model.*;

public class CreateTodoList {

	private WebRequest webRequest;
	private HttpServletResponse response;

	public CreateTodoList(WebRequest webRequest, HttpServletResponse response) {
		this.webRequest = webRequest;
		this.response = response;
    }

	public void service() throws IOException {
	    service(UUID.randomUUID().toString());
    }

	void service(String id) throws IOException {
		DomainEventPublisher.instance().publish(new TodoListCreatedEvent(id, webRequest.getParameter("name")));
	    response.sendRedirect("/");
    }

}
