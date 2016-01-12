package it.xpug.todolist;

import static org.mockito.Mockito.*;

import javax.servlet.http.*;

public class TestCaseForCommand extends TestCaseWithEvents {

	protected WebRequest webRequest = mock(WebRequest.class);
	protected HttpServletResponse response = mock(HttpServletResponse.class);

}
