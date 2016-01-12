package it.xpug.todolist;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.*;

import javax.servlet.http.*;

import org.junit.*;

public class CreateTodoListTest extends TestCaseWithEvents {
	WebRequest webRequest = mock(WebRequest.class);
	HttpServletResponse response = mock(HttpServletResponse.class);
	CreateTodoList controller = new CreateTodoList();

	@Test
	public void createNewTodoList() throws IOException {
		when(webRequest.getParameter("name")).thenReturn("pippo");

		controller.service(webRequest, response);

		assertEquals(1, handledEvents.size());
		TodoListCreatedEvent domainEvent = (TodoListCreatedEvent) handledEvents.get(0);
		assertEquals("pippo", domainEvent.getName());
	}
}
