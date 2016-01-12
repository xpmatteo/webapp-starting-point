package it.xpug.todolist;

import java.io.*;

import javax.servlet.http.*;

import com.saasovation.common.domain.model.*;

public class CheckTodoItem extends Command {

	private TodoItemRepository repository;

	public CheckTodoItem(TodoItemRepository repository) {
		this.repository = repository;
    }

	public void service(WebRequest webRequest, HttpServletResponse response) throws IOException {
		String todoItemId = webRequest.getPathParameter(1);
		TodoItem todoItem = repository.find(todoItemId);
		DomainEventPublisher.instance().publish(new TodoItemCheckedEvent(todoItemId));
		response.sendRedirect("/todolists/" + todoItem.getTodoListId());
    }

}
