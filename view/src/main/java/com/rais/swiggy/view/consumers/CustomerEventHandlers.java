package com.rais.swiggy.view.consumers;

import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;

import org.springframework.beans.factory.annotation.Autowired;

import com.rais.swiggy.common.events.CustomerCreatedEvent;
import com.rais.swiggy.view.service.ViewService;

public class CustomerEventHandlers {

	@Autowired
	ViewService viewService;
	
	public DomainEventHandlers customerDomainEventHandlers() {
		return DomainEventHandlersBuilder
				.forAggregateType("com.rais.swiggy.customer.domain.Customer")
				.onEvent(CustomerCreatedEvent.class, this::handleCustomerCreated)
				.build();
	}

	public void handleCustomerCreated(DomainEventEnvelope<CustomerCreatedEvent> domainEvent) {
		System.out.println("handleCustomerCreated amount {} "
				+ domainEvent.getEvent().getCustomerName());
		viewService.saveCustomer(domainEvent.getEvent().getCustomerName());
	}
}
