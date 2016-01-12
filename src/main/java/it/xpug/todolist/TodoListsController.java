package it.xpug.todolist;

import java.util.*;

import com.saasovation.common.domain.model.*;

public class TodoListsController {
	public void onCreateNewList(String name) {
		String id = UUID.randomUUID().toString();
		publish(new TodoListCreatedEvent(id, name));
    }

	private void publish(DomainEvent aDomainEvent) {
	    DomainEventPublisher.instance().publish(aDomainEvent);
	}
}
