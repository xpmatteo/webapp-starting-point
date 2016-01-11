package it.xpug.todolist;

import com.saasovation.common.domain.model.*;

import it.xpug.toolkit.db.*;

public class TodoListMainPageProjection implements DomainEventSubscriber<DomainEvent> {

	private Database database;

	public TodoListMainPageProjection(Database database) {
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
		else if (aDomainEvent instanceof TodoItemCreatedEvent) {
	        TodoItemCreatedEvent event = (TodoItemCreatedEvent) aDomainEvent;
			String sql = "insert into todo_items_page_projection (id, todo_list_id, todo_item_text) values (?, ?, ?)";
			database.execute(sql, event.getId(), event.getTodoListId(), event.getTodoItemText());
        }
		else if (aDomainEvent instanceof TodoItemCheckedEvent) {
			TodoItemCheckedEvent event = (TodoItemCheckedEvent) aDomainEvent;
			String sql = "update todo_items_page_projection set done = true where id = ?";
			database.execute(sql, event.getId());
        }
    }

	@Override
    public Class<DomainEvent> subscribedToEventType() {
	    return DomainEvent.class;
    }

}
