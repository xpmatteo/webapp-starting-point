package it.xpug.todolist;

import com.saasovation.common.domain.model.*;

public class TodoItemCheckedEvent extends DomainEvent {

	private String todoItemId;

	public TodoItemCheckedEvent(String todoItemId) {
		this.todoItemId = todoItemId;
    }

}
