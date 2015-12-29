package it.xpug.todolist;

import com.saasovation.common.domain.model.*;

import it.xpug.toolkit.db.*;

public class TodoListsMainPageProjection implements DomainEventSubscriber<TodoListCreatedEvent> {

	private Database database;

	public TodoListsMainPageProjection(Database database) {
		this.database = database;
    }

	@Override
    public void handleEvent(TodoListCreatedEvent todoListCreatedEvent) {
		String sql = "insert into todo_lists_main_page_projection (id, name) values (?, ?)";
		database.execute(sql, todoListCreatedEvent.getId(), todoListCreatedEvent.getName());
    }

	@Override
    public Class<TodoListCreatedEvent> subscribedToEventType() {
	    return TodoListCreatedEvent.class;
    }

}
