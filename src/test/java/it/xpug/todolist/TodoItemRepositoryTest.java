package it.xpug.todolist;

import static org.junit.Assert.*;
import it.xpug.toolkit.db.*;

import org.junit.*;

public class TodoItemRepositoryTest {
	Database database = new TestDatabase();
	EventStore eventStore = new EventStore(database);
	TodoItemRepository repository = new TodoItemRepository(eventStore);

	@Test
    public void find() throws Exception {
	    eventStore.handleEvent(new TodoItemCreatedEvent("itemId", "listId", "text"));

	    TodoItem todoItem = repository.find("itemId");

	    assertEquals(false, todoItem.isDone());
	    assertEquals("listId", todoItem.getTodoListId());
    }

	@Test
    public void findChecked() throws Exception {
	    eventStore.handleEvent(new TodoItemCreatedEvent("itemId", "listId", "text"));
	    eventStore.handleEvent(new TodoItemCheckedEvent("itemId"));

	    TodoItem todoItem = repository.find("itemId");

	    assertEquals(true, todoItem.isDone());
	    assertEquals("listId", todoItem.getTodoListId());
    }

}
