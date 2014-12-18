package it.xpug.toolkit.http;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.junit.*;

public class ReusableJettyAppTest {

	private static ReusableJettyApp app = new ReusableJettyApp(TestServlet.class);

	@Test
	public void servletIsInvokedOnRoot() throws Exception {
		assertThat(get("/"), is("Hello!"));
	}

	@Test
	public void servletIsInvokedOnAnyArbitraryPath() throws Exception {
		assertThat(get("/pippo"), is("Hello!"));
	}

	@Test
	public void staticResourcesAreReturned() throws Exception {
		assertThat(get("/zot.txt"), is("zot"));
	}

	@Test
	public void ifAnIndexIsPresentThenItIsReturned() throws Exception {
		FileWriter writer = new FileWriter("src/test/webapp/index.html");
		writer.write("Index here");
		writer.close();
		assertThat(get("/"), is("Index here"));
	}

	@Before
	public void setupStaticFiles() throws Exception {
		File basedir = new File("src/test/webapp");
		basedir.mkdirs();
		assertTrue(basedir.toString(), basedir.exists());
		new File(basedir, "index.html").delete();
		createTextFile(basedir, "zot.txt", "zot");
	}

	private void createTextFile(File basedir, String fileName, String fileContents) throws IOException {
		FileWriter fileWriter = new FileWriter(new File(basedir, fileName));
		fileWriter.write(fileContents);
		fileWriter.close();
	}

	@BeforeClass
	public static void startTheApplication() throws Exception {
		app.start(8123, "src/test/webapp");
	}

	@AfterClass
	public static void shutdownTheApplication() throws Exception {
		app.shutdown();
	}

	private String get(String path) throws IOException {
		InputStream stream = new URL("http://localhost:" + 8123 + path).openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		return reader.readLine();
	}

	static class TestServlet extends HttpServlet {
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.getWriter().write("Hello!");
		}
	}
}
