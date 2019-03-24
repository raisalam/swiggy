package com.rais.swiggy.view.consumers;


import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.messaging.consumer.MessageConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerServiceMessagingConfiguration {

  @Bean
  public CustomerEventHandlers customerHistoryEventHandlers() {
    return new CustomerEventHandlers();
  }

  @Bean
  public DomainEventDispatcher customerDomainEventDispatcher(CustomerEventHandlers customerEventHandlers, MessageConsumer messageConsumer) {
    return new DomainEventDispatcher("customerDomainEventDispatcher", customerEventHandlers.customerDomainEventHandlers(), messageConsumer);
  }
}
