package it.xpug.todolist;

import it.xpug.toolkit.db.*;

public class TodoListRouter extends Router {

	private static final String ID_REGEX = "([-0-9a-fA-F]{36})";


	public TodoListRouter(Database database, EventStore eventStore) {

		onPost("/todoitems/" + ID_REGEX, () -> new CheckTodoItem(new TodoItemRepository(eventStore)));
		onPost("/todolists/?", () -> new CreateTodoList());
		onPost("/todolists/" + ID_REGEX, () -> new RenameTodoList()).withNonEmptyParameter("new_name");
		onPost("/todolists/" + ID_REGEX, () -> new CreateTodoItem()).withNonEmptyParameter("new_item");
		onGet("/todolists/" + ID_REGEX, () -> new ShowSingleList(database));
		onGet("/", () -> new ShowAllLists(database));
		onNotFound(() -> new ShowNotFound());
    }
}
