package it.xpug.todolist;

import static org.junit.Assert.*;

import org.junit.*;

public class TodoListRenamedEventTest {

	@Test
	public void testToString() {
		TodoListRenamedEvent event = new TodoListRenamedEvent("5678", "new name");
		assertEquals("TodoListRenamedEvent(5678, new name)", event.toString());
	}

}
