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
	public boolean equals(Object obj) {
	    if (!(obj instanceof TodoListCreatedEvent))
	    	return false;
	    TodoListCreatedEvent other = (TodoListCreatedEvent) obj;
	    return other.name.equals(this.name) && other.id.equals(this.id);
	}

	@Override
	public int hashCode() {
	    return id.hashCode();
	}

	@Override
	public String toString() {
	    return String.format("TodoListCreatedEvent(%s, %s)", id, name);
	}

}
