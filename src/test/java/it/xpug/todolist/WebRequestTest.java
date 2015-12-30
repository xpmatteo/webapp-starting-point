package it.xpug.todolist;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.*;

import org.junit.*;

public class WebRequestTest {
	HttpServletRequest anHttpServletRequest = mock(HttpServletRequest.class);
	WebRequest webRequest = new WebRequest(anHttpServletRequest);

	@Test
    public void matchParameters() throws Exception {
		when(anHttpServletRequest.getRequestURI()).thenReturn("/foo/1234");
		assertTrue("matches pattern", webRequest.matches("/foo/(\\d+)"));
		assertEquals("1234", webRequest.getUriParameter(1));
		assertEquals(1234, webRequest.getUriParameterAsInteger(1));
    }

	@Test
    public void doesNotMatchParameters() throws Exception {
		when(anHttpServletRequest.getRequestURI()).thenReturn("something else");
		assertFalse("should not match pattern", webRequest.matches("/foo/(\\d+)"));
    }

}