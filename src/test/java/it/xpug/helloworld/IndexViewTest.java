package it.xpug.helloworld;

import static org.junit.Assert.*;

import it.xpug.toolkit.html.*;

import org.junit.*;

public class IndexViewTest {

	@Test
	public void showMessage() {
		TemplateView view = new TemplateView("index.ftl");
		view.put("message", "Hello, world!");
		HtmlDocument html = new HtmlDocument(view.toHtml());
		System.out.println(html);
		assertEquals("Hello, world!", html.getNode("//p[@id='message']").getText());
	}

}
