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
		Consumer<WebRequest> answerFoo = mockAnswer("foo");
		Consumer<WebRequest> answerBar = mockAnswer("bar");
		router.onPath("/foo", answerFoo);
		router.onPath("/bar", answerBar);
		when(request.matches("/foo")).thenReturn(true);
		when(request.matches("/bar")).thenReturn(false);

		router.onRequest(request);

		verify(answerFoo).accept(request);
		verifyZeroInteractions(answerBar);
	}

}
