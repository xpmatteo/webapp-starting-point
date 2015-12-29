package it.xpug.todolist;

import static org.junit.Assert.*;
import it.xpug.toolkit.db.*;

import org.junit.*;

public class TodoListsMainPageProjectionTest {

	@Test
    public void testName() throws Exception {
		Database database = new TestDatabase();

		TodoListsMainPageProjection projection = new TodoListsMainPageProjection(database);

		projection.handleEvent(new TodoListCreatedEvent());


		ListOfRows rows = database.select("select * from todo_lists_main_page");
		assertEquals("name of list", rows.get(0).get("name"));
    }

}
