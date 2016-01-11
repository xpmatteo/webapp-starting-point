package it.xpug.todolist;

import static org.junit.Assert.*;

import org.junit.*;

import com.saasovation.common.domain.model.*;

public class TodoListRenamedEventTest {
	@Test
	public void testToString() {
		DomainEvent event = new TodoListRenamedEvent("5678", "new name");
		assertEquals("TodoListRenamedEvent(5678, new name)", event.toString());
	}
}
