package it.xpug.todolist;

import static java.lang.Class.*;
import it.xpug.toolkit.db.*;

import java.util.*;

import com.google.gson.*;
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

		String json = new Gson().toJson(aDomainEvent);

		database.execute(sql,
				new java.sql.Date(aDomainEvent.occurredOn().getTime()),
				aDomainEvent.eventVersion(),
				aDomainEvent.getClass().getSimpleName(),
				aDomainEvent.getId(),
				json.toString());
    }

	@Override
    public Class<DomainEvent> subscribedToEventType() {
	    return DomainEvent.class;
    }

	public List<DomainEvent> findEvents(String entityId) {
		String sql = "select * from domain_events where entityId = ? order by id asc";
		ListOfRows rows = database.select(sql, entityId);
		if (0 == rows.size())
			return null;

		List<DomainEvent> events = new ArrayList<>();
		for (Map<String, Object> row : rows.toCollection()) {
			try {
				Class<?> klass = forName("it.xpug.todolist." + row.get("eventtype"));
				String json = (String) row.get("params").toString();
				Object event = new Gson().fromJson(json, klass);
                events.add((DomainEvent) event);
            } catch (JsonSyntaxException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
		return events;
    }
}
