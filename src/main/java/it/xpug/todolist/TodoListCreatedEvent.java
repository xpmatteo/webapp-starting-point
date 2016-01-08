package it.xpug.todolist;

import com.saasovation.common.domain.model.*;

public class TodoListCreatedEvent extends DomainEvent {

	private String id;
	private String name;

	public TodoListCreatedEvent(String id, String name) {
		super();
		this.id = id;
		this.name = name;
    }

	public String getName() {
	    return name;
    }

	public String getId() {
	    return id;
    }

	@Override
	public String toString() {
	    return String.format("TodoListCreatedEvent(%s, %s)", id, name);
	}
}
