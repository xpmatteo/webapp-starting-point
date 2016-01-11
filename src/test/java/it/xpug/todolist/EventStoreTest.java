package it.xpug.todolist;

import static org.junit.Assert.*;

import java.util.*;

import it.xpug.toolkit.db.*;

import org.junit.*;

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
