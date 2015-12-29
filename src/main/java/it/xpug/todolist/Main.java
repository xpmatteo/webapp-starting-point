package it.xpug.todolist;

import static java.lang.Integer.*;
import it.xpug.toolkit.db.*;
import it.xpug.toolkit.http.*;


public class Main {
	private static final String DEVELOPMENT_DATABASE_URL = "postgres://myproject:secret@localhost:5432/myproject_development";
	private static final String DEFAULT_PORT = "8080";

	public static void main(String[] args) {
		DatabaseConfiguration configuration = new DatabaseConfiguration(getDatabaseUrl());
		TodoListServlet servlet = new TodoListServlet(configuration);
		ReusableJettyApp app = new ReusableJettyApp(servlet);
		app.start(getPort(), "src/main/webapp");
	}

	private static int getPort() {
		return valueOf(getenv("PORT", DEFAULT_PORT));
	}

	private static String getDatabaseUrl() {
		return getenv("DATABASE_URL", DEVELOPMENT_DATABASE_URL);
	}

	private static String getenv(String key, String defaultValue) {
		String value = System.getenv(key);
		if (null == value)
			return defaultValue;
		return value;
	}
}
