package it.xpug.todolist;

import com.saasovation.common.domain.model.*;

public class TodoListsController {

	public void onCreateNewList(String name) {
		DomainEventPublisher.instance().publish(new TodoListCreatedEvent(name));
    }

}
