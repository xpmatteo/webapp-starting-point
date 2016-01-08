package it.xpug.todolist;

import com.saasovation.common.domain.model.*;

public class TodoItemCreatedEvent extends DomainEvent {

	private String id;
	private String todoListId;
	private String todoItemText;

	public TodoItemCreatedEvent(String todoItemId, String todoListId, String todoItemText) {
		this.id = todoItemId;
		this.todoListId = todoListId;
		this.todoItemText = todoItemText;
    }

	public String getTodoItemText() {
	    return todoItemText;
    }

	public String getTodoListId() {
	    return todoListId;
    }

	public String getId() {
	    return id;
    }
}
