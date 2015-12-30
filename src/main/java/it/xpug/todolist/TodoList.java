package it.xpug.todolist;

public class TodoList {

	private String name;

	public TodoList(String name) {
		this.name = name;
    }

	public String getName() {
	    return name;
    }

	public void rename(String newName) {
		this.name = newName;
    }

	@Override
	public String toString() {
	    return String.format("TodoList(%s)", name);
	}
}
