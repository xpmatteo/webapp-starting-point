package it.xpug.helloworld;

import static org.junit.Assert.*;
import it.xpug.toolkit.http.*;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;

public class EndToEndTest {
	private static final int PORT = 9080;

	private static WebDriver driver;
	private static ReusableJettyApp app;

	@BeforeClass
	public static void startApp() {
		app = new ReusableJettyApp(HelloWorldServlet.class);
		app.start(PORT, "src/main/resources");
		driver = new FirefoxDriver();
	}

	@Before
	public void getMainPage() throws Exception {
		driver.get("http://localhost:" + PORT);
	}

	@AfterClass
	public static void quitDriver() {
		driver.quit();
	}

	@Test
	public void messageIsShown() {
		WebElement message = driver.findElement(By.id("message"));
		assertEquals("Hello, world!", message.getText());
	}

}
