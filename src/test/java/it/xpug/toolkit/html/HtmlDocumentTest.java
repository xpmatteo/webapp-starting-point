package it.xpug.toolkit.html;

import static org.junit.Assert.*;

import java.util.*;

import it.xpug.toolkit.html.HtmlDocument.ElementNotFound;

import org.junit.*;

public class HtmlDocumentTest {

	@Test
	public void returnsTextContent() throws Exception {
		String html = "<b><c>pippo</c></b>";
		HtmlDocument document = new HtmlDocument(html);
		assertEquals("pippo", document.getText());
		assertEquals("pippo", document.getChildren().get(0).getText());
	}

	@Test
	public void returnsChildren() throws Exception {
		String html = "<root><a/><b/><c/></root>";
		HtmlDocument document = new HtmlDocument(html);
		List<HtmlDocument> children = document.getChildren();
		assertEquals(3, children.size());
		assertEquals("a", children.get(0).getNodeName());
	}

	@Test
	public void ignoresWhitespaceNodes() throws Exception {
		String html = "<a> <b/> </a>";
		HtmlDocument document = new HtmlDocument(html);
		List<HtmlDocument> children = document.getChildren();
		assertEquals(1, children.size());
		assertEquals("b", children.get(0).getNodeName());
	}

	@Test(timeout=100)
	public void doesNotTryToResolveEntities() throws Exception {
		String doctype =
				"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">";
		String html = "<a></a>";
		HtmlDocument document = new HtmlDocument(doctype+html);
		assertEquals("a", document.getNodeName());
	}

	@Test
	public void treatsUndeclaredEntitiesAsText() throws Exception {
		new HtmlDocument("<p> perch&eacute; </p>");
	}

	@Test
	public void equalityIsByStructure() throws Exception {
		HtmlDocument a = new HtmlDocument("<a><b><c>pippo</c></b></a>");
		HtmlDocument equal = new HtmlDocument("<a><b><c>pippo</c></b></a>");
		HtmlDocument differentText = new HtmlDocument("<a><b><c>pluto</c></b></a>");
		HtmlDocument differentNodeName = new HtmlDocument("<X><b><c>pippo</c></b></X>");
		HtmlDocument differentStructure = new HtmlDocument("<a><c>pluto</c></a>");

		assertEquals("same", a, a);
		assertEquals("equal", a, equal);
		assertEquals("equal", equal, a);
		assertFalse("differentNodeName", a.equals(differentNodeName));
		assertFalse("differentStructure", a.equals(differentStructure));
		assertFalse("differentText", a.equals(differentText));
	}

	@Test
	public void returnsSubDocument() throws Exception {
		HtmlDocument parent = new HtmlDocument("<a><b><c>pippo</c></b></a>");
		HtmlDocument child = parent.getNode("//b");
		assertEquals(child, new HtmlDocument("<b><c>pippo</c></b>"));
	}

	@Test
	public void returnsNumberOfMatches() throws Exception {
		HtmlDocument xmlDocument = new HtmlDocument("<a><b><c>pippo</c></b></a>");
		assertEquals(0, xmlDocument.numberOfMatches("//foo"));
		assertEquals(1, xmlDocument.numberOfMatches("//b"));
	}

	@Test
	public void findsElementByXpath() throws Exception {
		HtmlDocument xmlDocument = new HtmlDocument("<a><b><c>pippo</c></b></a>");
		assertEquals("pippo", xmlDocument.getNode("//c").getText());
	}

	@Test
	public void shouldThrowExceptionIfMoreNodesMatch() throws Exception {
        HtmlDocument document = new HtmlDocument(
        		"<a>" +
        		"  <b>foo</b>" +
        		"  <b>bar</b>" +
        		"</a>");
		assertEquals(2, document.numberOfMatches("//b"));
		try {
			document.getNode("//b");
			fail("should Throw an Exception If More Nodes Match");
		} catch (ElementNotFound ex) {
			assertEquals("\"//b\": expected 1 node, found 2", ex.getMessage());
		}
	}

	@Test
	public void shouldThrowNoNodesMatch() throws Exception {
        HtmlDocument document = new HtmlDocument("<a></a>");
		assertEquals(0, document.numberOfMatches("//xyzzy"));
		try {
			document.getNode("//xyzzy");
			fail("should throw");
		} catch (ElementNotFound ex) {
			assertEquals("\"//xyzzy\": expected 1 node, found 0", ex.getMessage());
		}
	}

	@Test
	public void returnsAttributeValue() throws Exception {
        HtmlDocument document = new HtmlDocument("<a href='ciao'></a>");
		assertEquals("ciao", document.getAttribute("href"));
	}


}
