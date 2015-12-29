package it.xpug.todolist;

import com.saasovation.common.domain.model.*;

import it.xpug.toolkit.db.*;

public class TodoListsMainPageProjection implements DomainEventSubscriber<DomainEvent> {

	private Database database;

	public TodoListsMainPageProjection(Database database) {
		this.database = database;
    }

	@Override
    public void handleEvent(DomainEvent aDomainEvent) {
		if (aDomainEvent instanceof TodoListCreatedEvent) {
	        TodoListCreatedEvent event = (TodoListCreatedEvent) aDomainEvent;
			String sql = "insert into todo_lists_main_page_projection (id, name) values (?, ?)";
			database.execute(sql, event.getId(), event.getName());
        }
		else if (aDomainEvent instanceof TodoListRenamedEvent) {
	        TodoListRenamedEvent event = (TodoListRenamedEvent) aDomainEvent;
			String sql = "update todo_lists_main_page_projection set name = ? where id = ?";
			database.execute(sql, event.getNewName(), event.getId());
        }
    }

	@Override
    public Class<DomainEvent> subscribedToEventType() {
	    return DomainEvent.class;
    }

}
