package it.xpug.toolkit.html;

import static org.junit.Assert.*;
import it.xpug.toolkit.html.*;

import org.junit.*;

public class TemplateViewTest {

	@Test
	public void substitutesVariablesInTemplate() throws Exception {
		TemplateView view = new TemplateView("test.ftl", "src/test/freemarker");
		view.put("name", "sailor");
	    assertEquals("Hello, sailor!\n", view.toHtml());
	}

}
