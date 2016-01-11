package it.xpug.todolist;

import java.util.*;

import org.json.*;

import it.xpug.toolkit.db.*;

public class TodoItemRepository {

	private Database database;

	public TodoItemRepository(Database database) {
		this.database = database;
    }

	public TodoItem find(String todoItemId) {
		String sql = "select * from domain_events where entityId = ? order by id asc";
		ListOfRows rows = database.select(sql, todoItemId);
		if (0 == rows.size())
			return null;

		TodoItem todoItem = null;
		for (Map<String, Object> row : rows.toCollection()) {
			JSONObject params = new JSONObject(row.get("params").toString());
			switch((String) row.get("eventtype")) {
			case "TodoItemCreatedEvent":
				todoItem = new TodoItem(params.getString("todoListId"));
				break;
			case "TodoItemCheckedEvent":
				todoItem.setDone();
				break;
			}
        }

		return todoItem;
    }

}
