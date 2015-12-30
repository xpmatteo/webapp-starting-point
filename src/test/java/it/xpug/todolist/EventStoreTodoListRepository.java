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
		ListOfRows rows = database.select("select * from domain_events where entityId = ?", id);
		if (0 == rows.size())
			return null;

		Map<String, Object> row = rows.get(0);
		JSONObject json = new JSONObject(row.get("params").toString());
	    return new TodoList(json.getString("name"));
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
