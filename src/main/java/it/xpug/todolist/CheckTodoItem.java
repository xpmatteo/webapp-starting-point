package it.xpug.todolist;

import java.io.*;

import javax.servlet.http.*;

import com.saasovation.common.domain.model.*;

public class CheckTodoItem {

	private WebRequest webRequest;
	private HttpServletResponse response;
	private TodoItemRepository repository;

	public CheckTodoItem(WebRequest webRequest, HttpServletResponse response, TodoItemRepository repository) {
		this.webRequest = webRequest;
		this.response = response;
		this.repository = repository;
    }

	public void service() throws IOException {
		String todoItemId = webRequest.getUriParameter(1);
		TodoItem todoItem = repository.find(todoItemId);
		DomainEventPublisher.instance().publish(new TodoItemCheckedEvent(todoItemId));
		response.sendRedirect("/todolists/" + todoItem.getTodoListId());
    }

}
