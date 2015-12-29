package it.xpug.todolist;

import java.util.*;

import com.saasovation.common.domain.model.*;

public class InMemoryTodoListRepository implements DomainEventSubscriber<DomainEvent> {

	private Map<String, TodoList> todoListsById = new HashMap<String, TodoList>();

	public TodoList find(String id) {
	    return todoListsById.get(id);
    }

	@Override
    public Class<DomainEvent> subscribedToEventType() {
	    return DomainEvent.class;
    }

	@Override
    public void handleEvent(DomainEvent aDomainEvent) {
		if (aDomainEvent instanceof TodoListCreatedEvent) {
	        TodoListCreatedEvent event = (TodoListCreatedEvent) aDomainEvent;
	        todoListsById.put(event.getId(), new TodoList(event.getName()));
        } else if (aDomainEvent instanceof TodoListRenamedEvent) {
	        TodoListRenamedEvent event = (TodoListRenamedEvent) aDomainEvent;
	        find(event.getId()).rename(event.getNewName());
        }

    }

}
