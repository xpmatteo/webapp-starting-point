package it.xpug.todolist;

import static org.junit.Assert.*;
import it.xpug.toolkit.db.*;

import org.junit.*;

public class EventStoreTodoListRepositoryTest {

	private Database database = new TestDatabase();
	private EventStoreTodoListRepository repository = new EventStoreTodoListRepository(database);

	@Before
    public void setUp() throws Exception {
	    database.execute("truncate domain_events");
    }

	@Test
    public void create() throws Exception {
	    repository.handleEvent(new TodoListCreatedEvent("list-id", "a list"));

	    ListOfRows rows = database.select("select * from domain_events");
	    assertEquals(1, rows.size());

//	    TodoList foundTodoList = repository.find("list-id");
//	    assertEquals("a list", foundTodoList.getName());
    }

	@Test@Ignore
    public void notFound() throws Exception {
	    repository.handleEvent(new TodoListCreatedEvent("list-id", "a list"));
	    assertNull(repository.find("BAD ID"));
    }

	@Test@Ignore
    public void createAndRename() throws Exception {
	    repository.handleEvent(new TodoListCreatedEvent("111", "old name"));
	    repository.handleEvent(new TodoListRenamedEvent("111", "new name"));

	    TodoList foundTodoList = repository.find("111");
	    assertEquals("new name", foundTodoList.getName());
    }
}
