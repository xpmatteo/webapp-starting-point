package it.xpug.todolist;

import java.io.*;

import it.xpug.toolkit.db.*;
import it.xpug.toolkit.html.*;

import javax.servlet.http.*;

public class ShowSingleList extends Command {

	private Database database;

	public ShowSingleList(Database database) throws IOException {
		this.database = database;
    }

	public void service(WebRequest webRequest, HttpServletResponse response) throws IOException {
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
