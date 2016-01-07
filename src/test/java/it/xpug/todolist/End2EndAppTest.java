package it.xpug.todolist;

import static org.junit.Assert.*;
import it.xpug.toolkit.db.*;
import it.xpug.toolkit.html.*;
import it.xpug.toolkit.http.*;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;
import java.util.stream.*;

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
    public void noLists() throws Exception {
		get("/");

		assertEquals("before", 0, myLists().size());
    }

	@Test
	public void addingANewTodoList() throws Exception {
		post("/todolists", "name=list-name");

		get("/");
		assertEquals("after", 1, myLists().size());
		assertEqualsAfterTrimming("list-name", myLists().get(0).getTextContent());
	}

	@Test
	public void renamingATodoList() throws Exception {
		post("/todolists", "name=old%20name");
		post(myListsUrls().get(0), "new_name=NEW%20NAME");

		get("/");
		assertEquals("after", 1, myLists().size());
		assertEqualsAfterTrimming("NEW NAME", myLists().get(0).getTextContent());
	}

	@Test@Ignore
	public void addingATodoItem() throws Exception {
		String path = createNewTodoList();
		post(path, "new_item=something-to-do");

		get(path);

		assertEquals("we have an item", 1, myTodoItems().size());
		assertEquals("something-to-do", myTodoItems().get(0));
	}

	private String createNewTodoList() throws IOException {
	    post("/todolists", "name=mylist");
		String path = myListsUrls().get(0);
	    return path;
    }


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

	private List<XmlNode> myTodoItems() {
	    return responseBody.getNodes("//ul[@id='my-list']/li");
	}

	private List<String> myListsUrls() {
	    return responseBody.getNodes("//ul[@id='my-lists']//a")
	    		.stream().map(node -> node.getAttribute("href")).collect(Collectors.toList());
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
