package it.xpug.todolist;

public class TodoListRouter extends Router {

	public TodoListRouter() {
		onNotFound(() -> new ShowNotFound());
    }
}
