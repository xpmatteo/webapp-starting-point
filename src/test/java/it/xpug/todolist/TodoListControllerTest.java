package it.xpug.todolist;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

import com.saasovation.common.domain.model.*;

public class TodoListControllerTest implements DomainEventSubscriber<DomainEvent> {
	private List<DomainEvent> handledEvents = new ArrayList<>();
	private InMemoryTodoListRepository repository = new InMemoryTodoListRepository();
	private TodoListsController controller = new TodoListsController();

	@Before
    public void setUp() throws Exception {
	    DomainEventPublisher.instance().subscribe(this);
	    DomainEventPublisher.instance().subscribe(repository);
    }

	@Test
	public void createNewTodoList() {
		controller.onCreateNewList("pippo");

		assertEquals(1, handledEvents.size());
		TodoListCreatedEvent domainEvent = (TodoListCreatedEvent) handledEvents.get(0);
		assertEquals("pippo", domainEvent.getName());
	}

	@Test
	public void renameTodoList() {
		repository.handleEvent(new TodoListCreatedEvent("123", "pluto"));

		controller.onRenameList("123", "pippo");

		assertEquals(1, handledEvents.size());
		assertThat(handledEvents, hasItem(equalTo(new TodoListRenamedEvent("123", "pippo"))));

		TodoList foundTodoList = repository.find("123");
		assertEquals("pippo", foundTodoList.getName());
	}

	@Test
	public void addTodoItem() {
		repository.handleEvent(new TodoListCreatedEvent("777", "my list"));

		controller.onAddTodoItem("777", "111", "buy milk");

		assertEquals(1, handledEvents.size());
		assertThat(handledEvents, hasItem(equalTo(new TodoItemCreatedEvent("777", "111", "buy milk"))));

		TodoItemCreatedEvent domainEvent = (TodoItemCreatedEvent) handledEvents.get(0);
		assertEquals("buy milk", domainEvent.getTodoItemText());
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
