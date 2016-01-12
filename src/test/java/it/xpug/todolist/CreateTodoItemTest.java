package it.xpug.todolist;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.*;

import org.junit.*;

public class CreateTodoItemTest extends TestCaseForCommand {

	@Test
	public void addTodoItem() throws IOException {
		when(webRequest.getParameter("new_item")).thenReturn("buy milk");
		when(webRequest.getPathParameter(1)).thenReturn("777");
		when(webRequest.getPath()).thenReturn("/todolists/777");

		new CreateTodoItem().service(webRequest, response, "111");

		assertEquals(1, handledEvents.size());
		assertThat(handledEvents, hasItem(equalTo(new TodoItemCreatedEvent("111", "777", "buy milk"))));
		verify(response).sendRedirect("/todolists/777");
	}


}
