package it.xpug.helloworld;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

public class TodoListsServletTest {




	@Test
	public void test() {

		List<TodoList> lists = new ArrayList<>();

		TodoListsController controller = new TodoListsController(lists);
		controller.onCreateNewList("pippo");

		assertEquals(1, lists.size());
		assertEquals("pippo", lists.get(0).getName());
	}

}
