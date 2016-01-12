package it.xpug.todolist;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.*;

import javax.servlet.http.*;

import org.junit.*;

import com.saasovation.common.domain.model.*;

public class CheckTodoItemControllerTest implements DomainEventSubscriber<DomainEvent>{

	WebRequest webRequest = mock(WebRequest.class);
	HttpServletResponse response = mock(HttpServletResponse.class);
	TodoItemRepository repository = mock(TodoItemRepository.class);
	CheckTodoItem controller = new CheckTodoItem(webRequest, response, repository);

	@Test
    public void markTodoItemDone() throws Exception {
		when(webRequest.getUriParameter(1)).thenReturn("1111");
		TodoItem todoItem = new TodoItem("abcd");
		when(repository.find("1111")).thenReturn(todoItem);

		controller.service();

		assertThat(handledEvents , hasItem(equalTo(new TodoItemCheckedEvent("1111"))));
		verify(response).sendRedirect("/todolists/abcd");
    }

	private List<DomainEvent> handledEvents = new ArrayList<>();

	@Before
    public void setUp() throws Exception {
	    DomainEventPublisher.instance().subscribe(this);
    }

	@Override
    public void handleEvent(DomainEvent aDomainEvent) {
		this.handledEvents.add(aDomainEvent);
    }

	@Override
    public Class<DomainEvent> subscribedToEventType() {
	    return DomainEvent.class;
    }

}
