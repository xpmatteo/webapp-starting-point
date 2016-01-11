package it.xpug.todolist;

import java.util.*;

import org.junit.*;

import com.saasovation.common.domain.model.*;

public class TestCaseWithEvents implements DomainEventSubscriber<DomainEvent>{

	protected List<DomainEvent> handledEvents = new ArrayList<>();

	@Before
    public void subscribeMyself() throws Exception {
	    DomainEventPublisher.instance().subscribe(this);
    }

	@Override
    public void handleEvent(DomainEvent aDomainEvent) {
		handledEvents.add(aDomainEvent);
    }

	@Override
    public Class<DomainEvent> subscribedToEventType() {
	    return DomainEvent.class;
    }

}
