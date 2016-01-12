package it.xpug.todolist;

import java.util.*;

import com.saasovation.common.domain.model.*;

public class TodoListsController {
	public void onCreateNewList(String name) {
		String id = UUID.randomUUID().toString();
		publish(new TodoListCreatedEvent(id, name));
    }

	public void onAddTodoItem(String todoListId, String newItem) {
		String todoItemId = UUID.randomUUID().toString();
		onAddTodoItem(todoListId, todoItemId, newItem);
    }

	public void onAddTodoItem(String todoListId, String todoItemId, String newItem) {
		publish(new TodoItemCreatedEvent(todoItemId, todoListId, newItem));
    }

	private void publish(DomainEvent aDomainEvent) {
	    DomainEventPublisher.instance().publish(aDomainEvent);
	}
}
