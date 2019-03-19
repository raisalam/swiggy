package com.rais.swiggy.view.consumers;


import com.rais.swiggy.common.events.OrderCreatedEvent;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;

public class OrderHistoryEventHandlers {


  public DomainEventHandlers domainEventHandlers() {
    return DomainEventHandlersBuilder
            .forAggregateType("com.rais.swiggy.order.domain.Order")
            .onEvent(OrderCreatedEvent.class, this::handleOrderCreated)
//            .onEvent(DeliveryPickedUp.class, this::handleDeliveryPickedUp)
            .build();
  }



  public void handleOrderCreated(DomainEventEnvelope<OrderCreatedEvent> dee) {
    System.out.println("handleOrderCreated called {}");

  }
}
