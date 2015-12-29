package it.xpug.todolist;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

import com.saasovation.common.domain.model.*;

public class TodoListsControllerTest implements DomainEventSubscriber<DomainEvent> {

	private List<DomainEvent> handledEvents = new ArrayList<>();

	@Before
    public void setUp() throws Exception {
	    DomainEventPublisher.instance().subscribe(this);
    }

	@Test
	public void createNewTodoListWithEvents() {
		TodoListsController controller = new TodoListsController();
		controller.onCreateNewList("pippo");

		assertEquals(1, handledEvents.size());
		assertThat(handledEvents, hasItem(equalTo(new TodoListCreatedEvent("pippo"))));
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
