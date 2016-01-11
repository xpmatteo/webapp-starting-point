package it.xpug.todolist;

import java.util.*;

import com.saasovation.common.domain.model.*;

public class TodoListRepository {

	private EventStore eventStore;

	public TodoListRepository(EventStore eventStore) {
		this.eventStore = eventStore;
    }

	public TodoList find(String id) {
		List<DomainEvent> events = eventStore.findEvents(id);
		if (events.isEmpty())
			return null;

		TodoList todoList = new TodoList();
		for (DomainEvent domainEvent : events) {
	        domainEvent.applyTo(todoList);
        }

		return todoList;
    }

}
