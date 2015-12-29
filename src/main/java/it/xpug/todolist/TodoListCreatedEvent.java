package it.xpug.todolist;

import java.util.*;

import com.saasovation.common.domain.model.*;

public class TodoListCreatedEvent implements DomainEvent {

	@Override
    public int eventVersion() {
	    return 0;
    }

	@Override
    public Date occurredOn() {
	    return null;
    }

	@Override
	public boolean equals(Object obj) {
	    return obj instanceof TodoListCreatedEvent;
	}

}
