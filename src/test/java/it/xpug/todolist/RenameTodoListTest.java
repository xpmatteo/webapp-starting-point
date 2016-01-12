package it.xpug.todolist;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.*;

import javax.servlet.http.*;

import org.junit.*;

public class RenameTodoListTest extends TestCaseWithEvents {
	WebRequest webRequest = mock(WebRequest.class);
	HttpServletResponse response = mock(HttpServletResponse.class);

	@Test
	public void renameTodoList() throws IOException {
		when(webRequest.getPath()).thenReturn("/todolists/123");
		when(webRequest.getPathParameter(1)).thenReturn("123");
		when(webRequest.getParameter("new_name")).thenReturn("pippo");

		new RenameTodoList().service(webRequest, response);

		assertEquals(1, handledEvents.size());
		assertThat(handledEvents, hasItem(equalTo(new TodoListRenamedEvent("123", "pippo"))));
		verify(response).sendRedirect("/todolists/123");
	}


}
