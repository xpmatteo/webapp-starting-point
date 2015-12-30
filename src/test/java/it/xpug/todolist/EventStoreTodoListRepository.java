package it.xpug.todolist;

import it.xpug.toolkit.db.*;

import org.json.*;

import com.saasovation.common.domain.model.*;

public class EventStoreTodoListRepository implements DomainEventSubscriber<DomainEvent> {

	private Database database;

	public EventStoreTodoListRepository(Database database) {
		this.database = database;
    }

	@Override
    public void handleEvent(DomainEvent aDomainEvent) {
		if (aDomainEvent instanceof TodoListCreatedEvent) {
	        TodoListCreatedEvent event = (TodoListCreatedEvent) aDomainEvent;

	        String sql = "insert into domain_events (occurredOn, version, eventType, params) "
	        		+ "values (?,?,?, cast(? as json))";
			database.execute(sql,
					new java.sql.Date(event.occurredOn().getTime()),
					event.eventVersion(),
					event.getClass().getSimpleName(),
					JSONObject.wrap(event).toString());
        }
    }

	@Override
    public Class<DomainEvent> subscribedToEventType() {
	    return DomainEvent.class;
    }

	public TodoList find(String id) {
	    return null;
    }

}
