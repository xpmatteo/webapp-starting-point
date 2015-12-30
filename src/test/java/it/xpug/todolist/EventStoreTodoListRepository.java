package it.xpug.todolist;

import com.saasovation.common.domain.model.*;

public class EventStoreTodoListRepository implements DomainEventSubscriber<DomainEvent> {

	@Override
    public void handleEvent(DomainEvent aDomainEvent) {

    }

	@Override
    public Class<DomainEvent> subscribedToEventType() {
	    return DomainEvent.class;
    }

	public TodoList find(String id) {
	    return null;
    }

}
