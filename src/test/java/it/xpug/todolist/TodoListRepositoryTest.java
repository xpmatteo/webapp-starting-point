package it.xpug.todolist;

import static org.junit.Assert.*;
import it.xpug.toolkit.db.*;

import org.junit.*;

public class TodoListRepositoryTest {

	private TodoListRepository repository;
	private Database database;

	@Before
	public void setUp() throws Exception {
		DatabaseConfiguration configuration = new DatabaseConfiguration(DatabaseTest.TEST_DATABASE_URL);
		database = new Database(configuration);
		repository = new TodoListRepository(database);
	}

	@Test@Ignore
    public void createNewList() throws Exception {
		TodoList todoList = new TodoList("Foobar");

		repository.add(todoList);

		ListOfRows rows = database.select("select * from events");
		assertEquals(1, rows.size());
    }

}
