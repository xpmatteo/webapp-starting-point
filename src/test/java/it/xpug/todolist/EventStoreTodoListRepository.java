package it.xpug.todolist;

import java.util.*;

import it.xpug.toolkit.db.*;

import org.json.*;

import com.saasovation.common.domain.model.*;

public class EventStoreTodoListRepository implements DomainEventSubscriber<DomainEvent> {

	private Database database;

	public EventStoreTodoListRepository(Database database) {
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

	@Override
    public void handleEvent(DomainEvent aDomainEvent) {
		String sql = "insert into domain_events (occurredOn, version, eventType, entityId, params) "
				+ "values (?, ?, ?, ?, cast(? as json))";
		JSONObject json = (JSONObject) JSONObject.wrap(aDomainEvent);
		database.execute(sql,
				new java.sql.Date(aDomainEvent.occurredOn().getTime()),
				aDomainEvent.eventVersion(),
				aDomainEvent.getClass().getSimpleName(),
				json.getString("id"),
				json.toString());
    }

	@Override
    public Class<DomainEvent> subscribedToEventType() {
	    return DomainEvent.class;
    }


}
