package it.xpug.todolist;

import static java.util.Arrays.*;
import static org.junit.Assert.*;

import java.util.*;

import it.xpug.toolkit.db.*;

import org.junit.*;

import com.saasovation.common.domain.model.*;

public class EventStoreTest {

	private Database database = new TestDatabase();
	private EventStore eventStore = new EventStore(database);

	@Before
    public void setUp() throws Exception {
	    database.execute("truncate domain_events");
    }

	@Test
    public void createEventDatabaseRow() throws Exception {
	    eventStore.handleEvent(new TodoListCreatedEvent("333", "a list"));

	    ListOfRows rows = database.select("select * from domain_events");
	    assertEquals(1, rows.size());
	    Map<String, Object> row = rows.get(0);
		assertEquals(0, row.get("version"));
	    assertEquals("TodoListCreatedEvent", row.get("eventtype"));
	    assertEquals("{\"name\":\"a list\",\"id\":\"333\"}", row.get("params").toString());
    }

	@Test
    public void findEvents() throws Exception {
	    TodoListCreatedEvent a = new TodoListCreatedEvent("333", "a list");
	    TodoListCreatedEvent b = new TodoListCreatedEvent("444", "a list");
	    TodoListRenamedEvent c = new TodoListRenamedEvent("333", "other name");
		eventStore.handleEvent(a);
		eventStore.handleEvent(b);
		eventStore.handleEvent(c);

	    List<DomainEvent> events = eventStore.findEvents("333");
		assertEquals(asList(a, c), events);
    }


	@Test
    public void renamedEventDatabaseRow() throws Exception {
	    eventStore.handleEvent(new TodoListRenamedEvent("888", "blah"));

	    ListOfRows rows = database.select("select * from domain_events");
	    assertEquals(1, rows.size());
	    Map<String, Object> row = rows.get(0);
		assertEquals(0, row.get("version"));
	    assertEquals("TodoListRenamedEvent", row.get("eventtype"));
	    assertEquals("{\"newName\":\"blah\",\"id\":\"888\"}", row.get("params").toString());
    }




}
