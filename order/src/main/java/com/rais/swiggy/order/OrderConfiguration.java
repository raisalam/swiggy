package com.rais.swiggy.order;

import com.rais.swiggy.order.domain.OrderDomainEventPublisher;
import com.rais.swiggy.order.saga.CreateOrderSaga;
import com.rais.swiggy.order.saga.CreateOrderSagaData;
import com.rais.swiggy.order.sagaparticipants.ConsumerServiceProxy;
import com.rais.swiggy.order.service.OrderCommandHandler;
import com.rais.swiggy.order.service.OrderService;
import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.sagas.orchestration.Saga;
import io.eventuate.tram.sagas.orchestration.SagaManager;
import io.eventuate.tram.sagas.orchestration.SagaManagerImpl;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@EnableAutoConfiguration
public class OrderConfiguration {

  @Bean
  public OrderService orderService() {
    return new OrderService();
  }


  @Bean
  public SagaManager<CreateOrderSagaData> createOrderSagaManager(Saga<CreateOrderSagaData> saga) {
    return new SagaManagerImpl<>(saga);
  }


  @Bean
  public CreateOrderSaga createOrderSaga(ConsumerServiceProxy consumerServiceProxy) {
    return new CreateOrderSaga(consumerServiceProxy);
  }

  @Bean
  public OrderCommandHandler orderCommandHandler() {
    return new OrderCommandHandler();
  }

  @Bean
  public ConsumerServiceProxy consumerServiceProxy() {
    return new ConsumerServiceProxy();
  }

  @Bean
  public OrderDomainEventPublisher orderAggregateEventPublisher(DomainEventPublisher eventPublisher) {
    return new OrderDomainEventPublisher(eventPublisher);
  }

  @Bean
  public CommandDispatcher orderCommandDispatcher(OrderCommandHandler target) {
    return new SagaCommandDispatcher("orderCommandDispatcher", target.commandHandlerDefinitions());
  }

}
