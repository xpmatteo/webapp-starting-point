package it.xpug.todolist;

import java.util.*;

import com.saasovation.common.domain.model.*;

public class TodoListsController {

	public void onCreateNewList(String name) {
		String id = UUID.randomUUID().toString();
		DomainEventPublisher.instance().publish(new TodoListCreatedEvent(id, name));
    }

	public void onRenameList(String id, String newName) {
		DomainEventPublisher.instance().publish(new TodoListRenamedEvent(id, newName));
    }

	public void onAddTodoItem(String id, String newItem) {
    }

}
