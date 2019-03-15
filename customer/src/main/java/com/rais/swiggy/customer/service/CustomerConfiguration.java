package com.rais.swiggy.customer.service;

import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaLockManager;
import io.eventuate.tram.sagas.participant.SagaParticipantConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Import(SagaParticipantConfiguration.class)
@EnableJpaRepositories
@EnableAutoConfiguration
public class CustomerConfiguration {

  @Bean
  public CustomerService customerService() {
    return new CustomerService();
  }

  @Bean
  public CustomerCommandHandler customerCommandHandler() {
    return new CustomerCommandHandler();
  }

  // TODO Exception handler for CustomerCreditLimitExceededException

  @Bean
  public CommandDispatcher consumerCommandDispatcher(CustomerCommandHandler target,
                                                     SagaLockManager sagaLockManager) {

    return new SagaCommandDispatcher("customerCommandDispatcher", target.commandHandlerDefinitions());
  }

}
