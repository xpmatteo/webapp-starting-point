package it.xpug.todolist;

import it.xpug.toolkit.db.*;

public class TestDatabaseConnectionFactory extends ConnectionFactory {

	public TestDatabaseConnectionFactory() {
	    super("postgres://myproject:secret@localhost:5432/myproject_test");
    }


}
