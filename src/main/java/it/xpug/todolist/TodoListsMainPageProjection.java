package it.xpug.todolist;

import com.saasovation.common.domain.model.*;

import it.xpug.toolkit.db.*;

public class TodoListsMainPageProjection implements DomainEventSubscriber<TodoListCreatedEvent> {

	public TodoListsMainPageProjection(Database database) {
    }

	@Override
    public void handleEvent(TodoListCreatedEvent aDomainEvent) {

    }

	@Override
    public Class<TodoListCreatedEvent> subscribedToEventType() {
	    return TodoListCreatedEvent.class;
    }

}
