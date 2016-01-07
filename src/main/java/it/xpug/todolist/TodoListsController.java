package it.xpug.todolist;

import java.util.*;

import com.saasovation.common.domain.model.*;

public class TodoListsController {
	public void onCreateNewList(String name) {
		String id = UUID.randomUUID().toString();
		publish(new TodoListCreatedEvent(id, name));
    }

	public void onRenameList(String id, String newName) {
		publish(new TodoListRenamedEvent(id, newName));
    }

	public void onAddTodoItem(String id, String newItem) {
		publish(new TodoItemCreatedEvent(id, newItem));
    }

	private void publish(DomainEvent aDomainEvent) {
	    DomainEventPublisher.instance().publish(aDomainEvent);
	}
}
