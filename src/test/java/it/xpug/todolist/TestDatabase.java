package it.xpug.todolist;

import it.xpug.toolkit.db.*;

public class TestDatabase extends Database {

	public TestDatabase() {
	    super(new DatabaseConfiguration("postgres://myproject:secret@localhost:5432/myproject_test"));
    }


}
