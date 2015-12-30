package it.xpug.todolist;

import static org.junit.Assert.*;

import java.util.*;

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
    public void createEventDatabaseRow() throws Exception {
	    repository.handleEvent(new TodoListCreatedEvent("333", "a list"));

	    ListOfRows rows = database.select("select * from domain_events");
	    assertEquals(1, rows.size());
	    Map<String, Object> row = rows.get(0);
		assertEquals(0, row.get("version"));
	    assertEquals("TodoListCreatedEvent", row.get("eventtype"));
	    assertEquals("{\"name\":\"a list\",\"id\":\"333\"}", row.get("params").toString());
    }

	@Test
    public void renamedEventDatabaseRow() throws Exception {
	    repository.handleEvent(new TodoListRenamedEvent("888", "blah"));

	    ListOfRows rows = database.select("select * from domain_events");
	    assertEquals(1, rows.size());
	    Map<String, Object> row = rows.get(0);
		assertEquals(0, row.get("version"));
	    assertEquals("TodoListRenamedEvent", row.get("eventtype"));
	    assertEquals("{\"newName\":\"blah\",\"id\":\"888\"}", row.get("params").toString());
    }


	@Test
    public void create() throws Exception {
	    repository.handleEvent(new TodoListCreatedEvent("1111", "a list"));

	    TodoList foundTodoList = repository.find("1111");
	    assertEquals("a list", foundTodoList.getName());
    }

	@Test
    public void notFound() throws Exception {
	    repository.handleEvent(new TodoListCreatedEvent("list-id", "a list"));
	    assertNull(repository.find("BAD ID"));
    }

	@Test
    public void createAndRename() throws Exception {
	    repository.handleEvent(new TodoListCreatedEvent("111", "old name"));
	    repository.handleEvent(new TodoListRenamedEvent("111", "some other name"));
	    repository.handleEvent(new TodoListRenamedEvent("111", "new name"));

	    TodoList foundTodoList = repository.find("111");
	    assertEquals("new name", foundTodoList.getName());
    }
}
