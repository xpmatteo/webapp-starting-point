package it.xpug.todolist;

import static org.junit.Assert.*;

import org.junit.*;

public class TodoListCreatedEventTest {

	@Test
    public void testEquals() throws Exception {
	    TodoListCreatedEvent base = new TodoListCreatedEvent("x", "zero");
	    TodoListCreatedEvent same = new TodoListCreatedEvent("x", "zero");
	    TodoListCreatedEvent differentId = new TodoListCreatedEvent("Y", "zero");
	    TodoListCreatedEvent differentName = new TodoListCreatedEvent("x", "uno");

	    assertNotEquals(base, null);
	    assertNotEquals(base, "not an event");
	    assertEquals(base, base);
	    assertEquals(base, same);
	    assertNotEquals(base, differentId);
	    assertNotEquals(base, differentName);
    }


	@Test
    public void testHashCode() throws Exception {
	    TodoListCreatedEvent base = new TodoListCreatedEvent("x", "foo");
	    TodoListCreatedEvent same = new TodoListCreatedEvent("x", "foo");

	    assertEquals(base.hashCode(), same.hashCode());
    }
}
