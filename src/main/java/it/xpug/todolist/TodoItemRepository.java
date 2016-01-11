package it.xpug.todolist;

import java.util.*;

import com.saasovation.common.domain.model.*;

public class TodoItemRepository {

	private EventStore eventStore;

	public TodoItemRepository(EventStore eventStore) {
		this.eventStore = eventStore;
    }

	public TodoItem find(String todoItemId) {
		TodoItem todoItem = new TodoItem();
		List<DomainEvent> findEvents = eventStore.findEvents(todoItemId);
		for (DomainEvent domainEvent : findEvents) {
	        domainEvent.applyTo(todoItem);
        }

		return todoItem;
    }

}
