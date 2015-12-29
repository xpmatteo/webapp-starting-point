package it.xpug.todolist;

import java.util.*;

import com.saasovation.common.domain.model.*;

public class TodoListsController {

	private List<TodoList> todoLists;

	public TodoListsController(List<TodoList> todoLists) {
		this.todoLists = todoLists;
    }

	public void onCreateNewList(String name) {
		todoLists.add(new TodoList(name));
		DomainEventPublisher.instance().publish(new TodoListCreatedEvent());
    }

}
