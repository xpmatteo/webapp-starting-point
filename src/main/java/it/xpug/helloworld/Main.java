package it.xpug.helloworld;

import it.xpug.toolkit.http.*;


public class Main {
	public static void main(String[] args) {
		ReusableJettyApp app = new ReusableJettyApp(HelloWorldServlet.class);
		app.start(8080, "src/main/webapp");
	}
}
