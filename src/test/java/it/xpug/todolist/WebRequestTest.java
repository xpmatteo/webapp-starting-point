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

	@Test
    public void isPost() throws Exception {
		HttpServletRequest postRequest = mock(HttpServletRequest.class, "post request");
		HttpServletRequest getRequest = mock(HttpServletRequest.class, "get request");
	    when(postRequest.getMethod()).thenReturn("POST");
	    when(getRequest.getMethod()).thenReturn("GET");

	    assertTrue("is post", new WebRequest(postRequest).isPost());
	    assertFalse("is get", new WebRequest(getRequest).isPost());
    }

	@Test
    public void getParameter() throws Exception {
	    when(anHttpServletRequest.getParameter("pippo")).thenReturn("pluto");

	    assertEquals("pluto", webRequest.getParameter("pippo"));
    }
}
