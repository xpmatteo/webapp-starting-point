package it.xpug.todolist;

public class TodoItem {

	private String todoListId;
	private boolean isDone;

	public TodoItem(String todoListId) {
		this.todoListId = todoListId;
    }

	public String getTodoListId() {
	    return todoListId;
    }

	public boolean isDone() {
	    return isDone;
    }

	public void setDone() {
		this.isDone = true;
    }

}
