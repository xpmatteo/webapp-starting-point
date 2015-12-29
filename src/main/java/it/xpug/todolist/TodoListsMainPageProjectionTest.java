package it.xpug.todolist;

import static org.junit.Assert.*;
import it.xpug.toolkit.db.*;

import org.junit.*;

public class TodoListsMainPageProjectionTest {

	@Test
    public void testName() throws Exception {
		Database database = new TestDatabase();
		database.execute("truncate todo_lists_main_page_projection");

		TodoListsMainPageProjection projection = new TodoListsMainPageProjection(database);

		projection.handleEvent(new TodoListCreatedEvent("name of list"));

		ListOfRows rows = database.select("select * from todo_lists_main_page_projection");
		assertEquals(1, rows.size());
		assertEquals("name of list", rows.get(0).get("name"));
    }

}
