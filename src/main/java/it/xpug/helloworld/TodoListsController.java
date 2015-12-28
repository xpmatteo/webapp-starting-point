package it.xpug.helloworld;

import java.util.*;

public class TodoListsController {

	private List<TodoList> todoLists;

	public TodoListsController(List<TodoList> todoLists) {
		this.todoLists = todoLists;
    }

	public void onCreateNewList(String name) {
		todoLists.add(new TodoList(name));
    }

}
