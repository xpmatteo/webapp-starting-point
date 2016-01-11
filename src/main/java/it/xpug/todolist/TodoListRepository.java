package it.xpug.todolist;

import java.util.*;

import org.json.*;

import it.xpug.toolkit.db.*;

public class TodoListRepository {

	private Database database;

	public TodoListRepository(Database database) {
		this.database = database;
    }

	public TodoList find(String id) {
		String sql = "select * from domain_events where entityId = ? order by id asc";
		ListOfRows rows = database.select(sql, id);
		if (0 == rows.size())
			return null;

		TodoList todoList = null;
		for (Map<String, Object> row : rows.toCollection()) {
			JSONObject params = new JSONObject(row.get("params").toString());
			switch((String) row.get("eventtype")) {
			case "TodoListCreatedEvent":
				todoList = new TodoList(params.getString("name"));
				break;
			case "TodoListRenamedEvent":
				todoList.rename(params.getString("newName"));
				break;
			}
        }
		return todoList;
    }

}
