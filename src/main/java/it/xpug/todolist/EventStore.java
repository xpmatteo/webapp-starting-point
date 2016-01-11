package it.xpug.todolist;

import it.xpug.toolkit.db.*;

import org.json.*;

import com.saasovation.common.domain.model.*;

public class EventStore implements DomainEventSubscriber<DomainEvent> {

	private Database database;

	public EventStore(Database database) {
		this.database = database;
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
