package it.xpug.toolkit.http;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.junit.*;


public class InitJettyWithServletInstanceAppTest {

	private static ReusableJettyApp app = new ReusableJettyApp(new TestServlet());

	@Test
	public void servletIsInvoked() throws Exception {
		assertThat(get("/foobar"), is("Hola!"));
	}

	static class TestServlet extends HttpServlet {
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.getWriter().write("Hola!");
		}
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
}
