package it.xpug.todolist;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;


public class RouterTest {
	WebRequest request = mock(WebRequest.class);
	Router router = new Router();

	@Test
	public void onNotFound() {
		Command command = mock(Command.class);
		router.defaultAnswer(() -> command);

		assertEquals(command, router.getCommandFor(request));
	}

	@Test
	public void responseOnSpecificPath() {
		when(request.matches("/foo")).thenReturn(true);
		when(request.matches("/bar")).thenReturn(false);

		Command answerFoo = mockCommand("foo");
		router.onAnyMethod("/foo", () -> answerFoo);
		router.onAnyMethod("/bar", () -> mockCommand("bar"));

		assertEquals(answerFoo, router.getCommandFor(request));
	}

	@Test
	public void responseOnSpecificPathAndMethod() {
		when(request.matches("/foo")).thenReturn(true);
		when(request.isPost()).thenReturn(true);

		Command answerPost = mockCommand("foo post");

		router.onPost("/foo", () -> answerPost);
		router.onAnyMethod("/foo", () -> mockCommand("foo get"));

		assertEquals(answerPost, router.getCommandFor(request));
	}

	@Test
	public void specifyingANonNullParameter_whenTheRequestHasIt() {
		when(request.matches("/foo")).thenReturn(true);
		when(request.getParameter("zot")).thenReturn("blah!");
		Command command = mockCommand();

		router.onAnyMethod("/foo", () -> command).withNonEmptyParameter("zot");
		assertEquals(command, router.getCommandFor(request));
	}

	@Test
	public void specifyingANonNullParameter_whenItIsNotPresent() {
		when(request.matches("/foo")).thenReturn(true);

		Command notFound = mockCommand("not found");
		router.defaultAnswer(() -> notFound);
		router.onAnyMethod("/foo", () -> mockCommand()).withNonEmptyParameter("zot");
		assertEquals(notFound, router.getCommandFor(request));
	}

	private Command mockCommand() {
	    return mockCommand("command");
    }

    private Command mockCommand(String name) {
	    return mock(Command.class, name);
    }
}
