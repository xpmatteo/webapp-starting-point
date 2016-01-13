package it.xpug.todolist;

import java.io.*;

import it.xpug.toolkit.db.*;
import it.xpug.toolkit.html.*;

import javax.servlet.http.*;

public class ShowAllLists extends Command {

	private Database database;

	public ShowAllLists(Database database) {
		this.database = database;
    }

	public void service(WebRequest request, HttpServletResponse response) throws IOException {
	    TemplateView view = new TemplateView("index.ftl");
		view.put("todoLists", database.select("select * from todo_lists_main_page_projection").toCollection());
		PrintWriter writer = response.getWriter();
		writer.write(view.toHtml());
		writer.close();
    }
}
