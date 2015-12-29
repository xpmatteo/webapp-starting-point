package it.xpug.todolist;

import java.util.*;

import com.saasovation.common.domain.model.*;

public class TodoListCreatedEvent implements DomainEvent {

	private String name;

	public TodoListCreatedEvent(String name) {
		this.name = name;
	}

	public String getName() {
	    return name;
    }

	@Override
    public int eventVersion() {
	    return 0;
    }

	@Override
    public Date occurredOn() {
	    return null;
    }

	@Override
	public boolean equals(Object obj) {
	    if (!(obj instanceof TodoListCreatedEvent))
	    	return false;
	    TodoListCreatedEvent other = (TodoListCreatedEvent) obj;
	    return other.name.equals(this.name);
	}

	@Override
	public int hashCode() {
	    return name.hashCode();
	}

	@Override
	public String toString() {
	    return String.format("TodoListCreatedEvent(%s)", name);
	}

}