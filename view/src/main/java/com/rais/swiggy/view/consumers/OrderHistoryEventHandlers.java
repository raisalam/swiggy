package com.rais.swiggy.view.consumers;

import com.rais.swiggy.common.events.OrderRejectedEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.rais.swiggy.common.events.OrderCreatedEvent;
import com.rais.swiggy.view.domain.OrderDetails;
import com.rais.swiggy.view.service.ViewService;

import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;

public class OrderHistoryEventHandlers {

	@Autowired
	ViewService viewService;

	public DomainEventHandlers domainEventHandlers() {
		return DomainEventHandlersBuilder.forAggregateType("com.rais.swiggy.order.domain.Order")
				.onEvent(OrderCreatedEvent.class, this::handleOrderCreated)
				.onEvent(OrderRejectedEvent.class, this::handleOrderRejected)
				.build();
	}

	public void handleOrderCreated(DomainEventEnvelope<OrderCreatedEvent> dee) {
		System.out.println("handleOrderCreated amount {}" + dee.getEvent().getOrderTotal().getAmount());
		OrderDetails orderDetail = new OrderDetails(dee.getEvent().getOrderDetails().getCustomerId(),
				dee.getEvent().getOrderDetails().getOrderTotal());
		viewService.saveOrder(orderDetail);

	}

	public void handleOrderRejected(DomainEventEnvelope<OrderRejectedEvent> dee) {
		System.out.println("handleOrderRejected amount {}" + dee.getEvent().getOrderTotal().getAmount());


	}
}
