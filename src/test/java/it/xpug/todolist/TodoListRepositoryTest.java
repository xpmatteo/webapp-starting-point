package it.xpug.todolist;

import static org.junit.Assert.*;
import it.xpug.toolkit.db.*;

import org.junit.*;

public class TodoListRepositoryTest {

	private Database database = new TestDatabase();
	private EventStore eventStore = new EventStore(database);
	private TodoListRepository todoLists = new TodoListRepository(database);

	@Before
    public void setUp() throws Exception {
	    database.execute("truncate domain_events");
    }

	@Test
    public void create() throws Exception {
	    eventStore.handleEvent(new TodoListCreatedEvent("1111", "a list"));

	    TodoList foundTodoList = todoLists.find("1111");
	    assertEquals("a list", foundTodoList.getName());
    }

	@Test
    public void notFound() throws Exception {
	    eventStore.handleEvent(new TodoListCreatedEvent("list-id", "a list"));
	    assertNull(todoLists.find("BAD ID"));
    }

	@Test
    public void createAndRename() throws Exception {
	    eventStore.handleEvent(new TodoListCreatedEvent("111", "old name"));
	    eventStore.handleEvent(new TodoListRenamedEvent("111", "some other name"));
	    eventStore.handleEvent(new TodoListRenamedEvent("111", "new name"));

	    TodoList foundTodoList = todoLists.find("111");
	    assertEquals("new name", foundTodoList.getName());
    }
}