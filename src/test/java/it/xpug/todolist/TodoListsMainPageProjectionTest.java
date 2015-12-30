package it.xpug.todolist;

import static org.junit.Assert.*;
import it.xpug.toolkit.db.*;

import org.junit.*;

public class TodoListsMainPageProjectionTest {

	Database database = new TestDatabase();
	TodoListMainPageProjection projection = new TodoListMainPageProjection(database);

	@Before
    public void setUp() throws Exception {
		database.execute("truncate todo_lists_main_page_projection");
    }

	@Test
    public void creation() throws Exception {

		projection.handleEvent(new TodoListCreatedEvent("some id", "name of list"));

		ListOfRows rows = database.select("select * from todo_lists_main_page_projection");
		assertEquals(1, rows.size());
		assertEquals("some id", rows.get(0).get("id"));
		assertEquals("name of list", rows.get(0).get("name"));
    }

	@Test
    public void renaming() throws Exception {

		projection.handleEvent(new TodoListCreatedEvent("1234", "old name"));
		projection.handleEvent(new TodoListRenamedEvent("1234", "NEW name"));

		ListOfRows rows = database.select("select * from todo_lists_main_page_projection");
		assertEquals(1, rows.size());
		assertEquals("NEW name", rows.get(0).get("name"));
    }

}
