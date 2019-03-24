package com.rais.swiggy.customer.service;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.rais.swiggy.customer.domain.CustomerDomainEventPublisher;

import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaLockManager;
import io.eventuate.tram.sagas.participant.SagaParticipantConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(SagaParticipantConfiguration.class)
@EnableJpaRepositories
@EnableAutoConfiguration
@EnableTransactionManagement
public class CustomerConfiguration {

  @Bean
  public CustomerService customerService() {
    return new CustomerService();
  }

  @Bean
  public CustomerCommandHandler customerCommandHandler() {
    return new CustomerCommandHandler();
  }

  @Bean
  public CommandDispatcher consumerCommandDispatcher(CustomerCommandHandler target,
                                                     SagaLockManager sagaLockManager) {

    return new SagaCommandDispatcher("customerCommandDispatcher", target.commandHandlerDefinitions());
  }
  
  @Bean
  public CustomerDomainEventPublisher customerAggregateEventPublisher(DomainEventPublisher eventPublisher) {
    return new CustomerDomainEventPublisher(eventPublisher);
  }

}
