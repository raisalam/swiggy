package com.rais.swiggy.view.consumers;


import io.eventuate.javaclient.spring.EnableEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.messaging.consumer.MessageConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEventHandlers
public class OrderHistoryServiceMessagingConfiguration {

  @Bean
  public OrderHistoryEventHandlers orderHistoryEventHandlers() {
    return new OrderHistoryEventHandlers();
  }

  @Bean
  public DomainEventDispatcher orderHistoryDomainEventDispatcher(OrderHistoryEventHandlers orderHistoryEventHandlers, MessageConsumer messageConsumer) {
    return new DomainEventDispatcher("orderHistoryDomainEventDispatcher", orderHistoryEventHandlers.domainEventHandlers(), messageConsumer);
  }
}
