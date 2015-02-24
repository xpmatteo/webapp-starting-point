package it.xpug.helloworld;

import it.xpug.toolkit.db.Database;
import it.xpug.toolkit.db.DatabaseConfiguration;
import it.xpug.toolkit.http.ReusableJettyApp;


public class Main {
	private static final String DEVELOPMENT_DATABASE_URL = "postgres://myproject:secret@localhost:5432/myproject_development";
	
	public static void main(String[] args) {
		DatabaseConfiguration configuration = new DatabaseConfiguration(getDatabaseUrl());
		Database database = new Database(configuration);
		ReusableJettyApp app = new ReusableJettyApp(new HelloWorldServlet(database));
		app.start(8080, "src/main/webapp");
	}

	private static String getDatabaseUrl() {
		String databaseUrl = System.getenv("DATABASE_URL");
		if (null == databaseUrl) 
			databaseUrl = DEVELOPMENT_DATABASE_URL;
		return databaseUrl;
	}
}
