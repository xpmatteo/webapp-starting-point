package it.xpug.todolist;

import static org.junit.Assert.*;
import it.xpug.toolkit.db.*;
import it.xpug.toolkit.html.*;
import it.xpug.toolkit.http.*;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;

import org.junit.*;

public class End2EndAppTest {

	private static ConnectionFactory configuration = new TestDatabaseConnectionFactory();
	private static ReusableJettyApp app = new ReusableJettyApp(new TodoListServlet(configuration));
	private XmlNode responseBody;

	@Before
    public void setUp() throws Exception {
	    Database database = new Database(configuration);
	    database.execute("truncate todo_lists_main_page_projection");
	    database.execute("truncate domain_events");
    }

	@Test
	public void mainFlow() throws Exception {
		get("/");

		assertEquals("before", 0, myLists().size());

		post("/todolists", "name=list-name");

		get("/");
		assertEquals("after", 1, myLists().size());
		assertEqualsAfterTrimming("list-name", myLists().get(0).getTextContent());
	}

	//	@Test
//	public void servletIsInvokedOnAnyArbitraryPath() throws Exception {
//		assertThat(get("/pippo"), is("Hello!"));
//	}
//
//	@Test
//	public void staticResourcesAreReturned() throws Exception {
//		assertThat(get("/zot.txt"), is("zot"));
//	}
//
//	@Test
//	public void ifAnIndexIsPresentThenItIsReturned() throws Exception {
//		FileWriter writer = new FileWriter("src/test/webapp/index.html");
//		writer.write("Index here");
//		writer.close();
//		assertThat(get("/"), is("Index here"));
//	}

	@BeforeClass
	public static void startTheApplication() throws Exception {
		app.start(8888, "src/test/webapp");
	}

	@AfterClass
	public static void shutdownTheApplication() throws Exception {
		app.shutdown();
	}

	private List<XmlNode> myLists() {
	    return responseBody.getNodes("//ul[@id='my-lists']/li");
	}

	private void assertEqualsAfterTrimming(String expected, String actual) {
		assertEquals(expected.trim(), actual.trim());
	}

	private String get(String path) throws IOException {
		try (InputStream stream = makeUrl(path).openStream()) {
			String bodyAsString = toString(stream);
			this.responseBody = new XmlNode(bodyAsString);
			return bodyAsString;
		}
	}

	private String post(String path, String urlParameters) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) makeUrl(path).openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.close();

		try (InputStream inputStream = connection.getInputStream()) {
			String bodyAsString = toString(inputStream);
			this.responseBody = new XmlNode(bodyAsString);
			return bodyAsString;
		}
	}

	private String toString(InputStream stream) throws IOException {
	    InputStreamReader reader = new InputStreamReader(stream, Charset.forName("UTF-8"));
		char[] buffer = new char[24000];
		int numRead = reader.read(buffer);
		return new String(buffer, 0, numRead);
    }

	private URL makeUrl(String path) throws MalformedURLException {
	    return new URL("http://localhost:" + 8888 + path);
    }

}
