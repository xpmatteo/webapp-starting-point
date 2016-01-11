package it.xpug.todolist;

import com.saasovation.common.domain.model.*;

public class TodoItemCheckedEvent extends DomainEvent {

	private String id;

	public TodoItemCheckedEvent(String todoItemId) {
		this.id = todoItemId;
    }

	public Object getId() {
	    return id;
    }

}
