package it.xpug.todolist;

import com.saasovation.common.domain.model.*;


public class TodoListRenamedEvent extends DomainEvent {
	private String id;
	private String newName;

	public TodoListRenamedEvent(String id, String newName) {
		super();
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
	public void applyTo(Object object) {
	    set(object, "name", newName);
	}
}
