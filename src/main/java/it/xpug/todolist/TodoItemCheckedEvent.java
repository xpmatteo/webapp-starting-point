package it.xpug.todolist;

import com.saasovation.common.domain.model.*;

public class TodoItemCheckedEvent extends DomainEvent {

	@SuppressWarnings("unused")
    private String id;
	private boolean done;

	public TodoItemCheckedEvent(String todoItemId, boolean done) {
		this.id = todoItemId;
		this.done = done;
    }

	@Override
	public void applyTo(Object object) {
	    TodoItem todoItem = (TodoItem) object;
	    set(todoItem, "isDone", done);
	}

	public boolean isDone() {
	    return done;
    }

}
