package it.xpug.todolist;

import static org.junit.Assert.*;

import org.junit.*;

public class InMemoryTodoListRepositoryTest {
	InMemoryTodoListRepository repository = new InMemoryTodoListRepository();

	@Test
    public void create() throws Exception {
	    repository.handleEvent(new TodoListCreatedEvent("list-id", "a list"));

	    TodoList foundTodoList = repository.find("list-id");
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
	    repository.handleEvent(new TodoListRenamedEvent("111", "new name"));

	    TodoList foundTodoList = repository.find("111");
	    assertEquals("new name", foundTodoList.getName());
    }

}
