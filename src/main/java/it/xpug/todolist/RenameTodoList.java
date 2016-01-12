package it.xpug.todolist;

import java.io.*;

import javax.servlet.http.*;

import com.saasovation.common.domain.model.*;

public class RenameTodoList extends Command {

	private WebRequest webRequest;
	private HttpServletResponse response;

	public RenameTodoList(WebRequest webRequest, HttpServletResponse response) {
		this.webRequest = webRequest;
		this.response = response;
    }

	public void service() throws IOException {
	    String id = webRequest.getPathParameter(1);
	    String newName = webRequest.getParameter("new_name");
	    DomainEventPublisher.instance().publish(new TodoListRenamedEvent(id, newName));
	    response.sendRedirect(webRequest.getPath());
    }

}
