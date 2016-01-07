package it.xpug.todolist;

import com.saasovation.common.domain.model.*;

public class TodoItemCreatedEvent extends DomainEvent {

	private String listId;
	private String todoItemText;

	public TodoItemCreatedEvent(String listId, String todoItemText) {
		this.listId = listId;
		this.todoItemText = todoItemText;
    }

	public String getTodoItemText() {
	    return todoItemText;
    }

	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((listId == null) ? 0 : listId.hashCode());
	    result = prime * result + ((todoItemText == null) ? 0 : todoItemText.hashCode());
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
	    if (listId == null) {
		    if (other.listId != null)
			    return false;
	    } else if (!listId.equals(other.listId))
		    return false;
	    if (todoItemText == null) {
		    if (other.todoItemText != null)
			    return false;
	    } else if (!todoItemText.equals(other.todoItemText))
		    return false;
	    return true;
    }
}
