package it.xpug.todolist;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

public class TodoListsControllerTest {

	@Test
	public void createNewTodoList() {

		List<TodoList> todoLists = new ArrayList<>();

		TodoListsController controller = new TodoListsController(todoLists);
		controller.onCreateNewList("pippo");

		assertEquals(1, todoLists.size());
		assertEquals("pippo", todoLists.get(0).getName());
	}

}
