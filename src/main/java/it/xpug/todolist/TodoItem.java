package it.xpug.todolist;

public class TodoItem {

	private String todoListId;
	private boolean isDone;

	public TodoItem(String todoListId) {
		this.todoListId = todoListId;
    }

	public TodoItem() {
    }

	public boolean isDone() {
	    return isDone;
    }

	public void setDone() {
		this.isDone = true;
    }

	public String getTodoListId() {
	    return todoListId;
	}

	public void setTodoListId(String todoListId) {
		this.todoListId = todoListId;
    }

}
