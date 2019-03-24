package com.rais.swiggy.customer.domain;

import com.rais.swiggy.common.events.CustomerEvent;

import io.eventuate.tram.events.aggregates.AbstractAggregateDomainEventPublisher;
import io.eventuate.tram.events.publisher.DomainEventPublisher;

public class CustomerDomainEventPublisher extends AbstractAggregateDomainEventPublisher<Customer, CustomerEvent> {

	public CustomerDomainEventPublisher(DomainEventPublisher eventPublisher) {
		super(eventPublisher, Customer.class, Customer::getId);
	}

}
