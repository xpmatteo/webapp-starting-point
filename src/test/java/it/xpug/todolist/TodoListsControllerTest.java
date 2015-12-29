package it.xpug.todolist;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.equalTo;
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
	public void createNewTodoList() {

		List<TodoList> todoLists = new ArrayList<>();

		TodoListsController controller = new TodoListsController(todoLists);
		controller.onCreateNewList("pippo");

		assertEquals(1, todoLists.size());
		assertEquals("pippo", todoLists.get(0).getName());
	}

	@Test
	public void createNewTodoListWithEvents() {
		List<TodoList> todoLists = new ArrayList<>();

		TodoListsController controller = new TodoListsController(todoLists);
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
