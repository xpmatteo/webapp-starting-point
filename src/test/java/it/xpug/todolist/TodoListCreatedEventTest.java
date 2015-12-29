package it.xpug.todolist;

import static org.junit.Assert.*;

import org.junit.*;

public class TodoListCreatedEventTest {

	@Test
    public void testEquals() throws Exception {
	    TodoListCreatedEvent base = new TodoListCreatedEvent("zero");
	    TodoListCreatedEvent same = new TodoListCreatedEvent("zero");
	    TodoListCreatedEvent differentName = new TodoListCreatedEvent("uno");

	    assertNotEquals(base, null);
	    assertNotEquals(base, "not an event");
	    assertEquals(base, base);
	    assertEquals(base, same);
	    assertNotEquals(base, differentName);
    }

}
