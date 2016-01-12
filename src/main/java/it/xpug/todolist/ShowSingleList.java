package it.xpug.todolist;

import java.io.*;

import it.xpug.toolkit.db.*;
import it.xpug.toolkit.html.*;

import javax.servlet.http.*;

public class ShowSingleList {

	private Database database;
	private WebRequest webRequest;
	private HttpServletResponse response;

	public ShowSingleList(WebRequest webRequest, HttpServletResponse response, Database database) throws IOException {
		this.database = database;
		this.webRequest = webRequest;
		this.response = response;
    }

	public void service() throws IOException {
	    String todoListId = webRequest.getPathParameter(1);
		ListOfRows todoLists = database.select("select * from todo_lists_main_page_projection where id = ?", todoListId);
		ListOfRows todoItems = database.select("select * from todo_items_page_projection where todo_list_id = ?", todoListId);
	    TemplateView view = new TemplateView("todo_list.ftl");
	    view.put("todoList", todoLists.get(0));
	    view.put("todoItems", todoItems.toCollection());
	    PrintWriter writer = response.getWriter();
	    writer.write(view.toHtml());
	    writer.close();
    }

}
