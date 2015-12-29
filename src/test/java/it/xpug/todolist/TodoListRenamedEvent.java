package it.xpug.todolist;

import java.util.*;

import com.saasovation.common.domain.model.*;


public class TodoListRenamedEvent implements DomainEvent {
	private String id;
	private String newName;

	public TodoListRenamedEvent(String id, String newName) {
		this.id = id;
		this.newName = newName;
    }

	public String getId() {
	    return id;
    }

	public String getNewName() {
	    return newName;
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
	    return obj instanceof TodoListRenamedEvent;
	}

	@Override
	public String toString() {
	    return String.format("TodoListRenamedEvent(%s, %s)", id, newName);
	}

}
