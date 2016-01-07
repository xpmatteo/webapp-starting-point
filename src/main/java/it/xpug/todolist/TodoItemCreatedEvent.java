package it.xpug.todolist;

import com.saasovation.common.domain.model.*;

public class TodoItemCreatedEvent extends DomainEvent {

	private String todoListId;
	private String todoItemId;
	private String todoItemText;

	public TodoItemCreatedEvent(String todoListId, String todoItemId, String todoItemText) {
		this.todoListId = todoListId;
		this.todoItemId = todoItemId;
		this.todoItemText = todoItemText;
    }

	public String getTodoItemText() {
	    return todoItemText;
    }

	public String getTodoListId() {
	    return todoListId;
    }

	public String getTodoItemId() {
	    return todoItemId;
    }

	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((todoItemId == null) ? 0 : todoItemId.hashCode());
	    result = prime * result + ((todoItemText == null) ? 0 : todoItemText.hashCode());
	    result = prime * result + ((todoListId == null) ? 0 : todoListId.hashCode());
	    return result;
    }

	@Override
    public boolean equals(Object obj) {
	    if (this == obj)
		    return true;
	    if (obj == null)
		    return false;
	    if (getClass() != obj.getClass())
		    return false;
	    TodoItemCreatedEvent other = (TodoItemCreatedEvent) obj;
	    if (todoItemId == null) {
		    if (other.todoItemId != null)
			    return false;
	    } else if (!todoItemId.equals(other.todoItemId))
		    return false;
	    if (todoItemText == null) {
		    if (other.todoItemText != null)
			    return false;
	    } else if (!todoItemText.equals(other.todoItemText))
		    return false;
	    if (todoListId == null) {
		    if (other.todoListId != null)
			    return false;
	    } else if (!todoListId.equals(other.todoListId))
		    return false;
	    return true;
    }


}
