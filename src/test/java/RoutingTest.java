import static org.mockito.Mockito.*;
import it.xpug.todolist.*;

import java.util.function.*;

import org.junit.*;


public class RoutingTest {

	WebRequest request = mock(WebRequest.class);
	Router router = new Router();

	@Test
	public void onNotFound() {
		Consumer<WebRequest> answer = mockAnswer("404");
		router.defaultAnswer(answer);

		router.onRequest(request);

		verify(answer).accept(request);
	}

	@SuppressWarnings("unchecked")
    private Consumer<WebRequest> mockAnswer(String name) {
	    return mock(Consumer.class, name);
    }

	@Test
	public void responseOnSpecificPath() {
		when(request.matches("/foo")).thenReturn(true);
		when(request.matches("/bar")).thenReturn(false);

		Consumer<WebRequest> answerFoo = mockAnswer("foo");
		Consumer<WebRequest> answerBar = mockAnswer("bar");
		router.onAnyMethod("/foo", answerFoo);
		router.onAnyMethod("/bar", answerBar);

		router.onRequest(request);

		verify(answerFoo).accept(request);
		verifyZeroInteractions(answerBar);
	}

	@Test
	public void responseOnSpecificPathAndMethod() {
		when(request.matches("/foo")).thenReturn(true);
		when(request.isPost()).thenReturn(true);

		Consumer<WebRequest> answerGet = mockAnswer("foo get");
		Consumer<WebRequest> answerPost = mockAnswer("foo post");
		router.onPost("/foo", answerPost);
		router.onAnyMethod("/foo", answerGet);

		router.onRequest(request);

		verifyZeroInteractions(answerGet);
		verify(answerPost).accept(request);
	}

}
