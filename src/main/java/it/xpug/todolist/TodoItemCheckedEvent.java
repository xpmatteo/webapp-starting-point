package it.xpug.todolist;

import com.saasovation.common.domain.model.*;

public class TodoItemCheckedEvent extends DomainEvent {

	@SuppressWarnings("unused")
    private String id;

	public TodoItemCheckedEvent(String todoItemId) {
		this.id = todoItemId;
    }

	@Override
	public void applyTo(Object object) {
	    TodoItem todoItem = (TodoItem) object;
	    todoItem.setDone();
	}

}
