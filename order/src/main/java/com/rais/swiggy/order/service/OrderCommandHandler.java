package com.rais.swiggy.order.service;

import com.rais.swiggy.common.events.OrderCreatedEvent;
import com.rais.swiggy.common.events.OrderEvent;
import com.rais.swiggy.common.events.OrderRejectedEvent;
import com.rais.swiggy.order.api.reply.ApproveOrderCommand;
import com.rais.swiggy.order.api.reply.RejectOrderCommand;
import com.rais.swiggy.order.domain.Order;
import com.rais.swiggy.order.domain.OrderDomainEventPublisher;
import com.rais.swiggy.order.domain.OrderRepository;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;
import static java.util.Collections.singletonList;

public class OrderCommandHandler {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderDomainEventPublisher orderAggregateEventPublisher;

  public CommandHandlers commandHandlerDefinitions() {
    return SagaCommandHandlersBuilder
            .fromChannel("orderService")
            .onMessage(ApproveOrderCommand.class, this::approve)
            .onMessage(RejectOrderCommand.class, this::reject)
            .build();
  }

  public Message approve(CommandMessage<ApproveOrderCommand> cm) {
    long orderId = cm.getCommand().getOrderId();
    Order order = orderRepository.findById(orderId).get();
    order.noteCreditReserved();
    return withSuccess();
  }

  public Message reject(CommandMessage<RejectOrderCommand> cm) {
    long orderId = cm.getCommand().getOrderId();
    Order order = orderRepository.findById(orderId).get();
    order.noteCreditReservationFailed();
    List<OrderEvent> events = singletonList(new OrderRejectedEvent());
    orderAggregateEventPublisher.publish(order, events );
    System.out.println("OrderCommandHandler :: reject() ");
    return withSuccess();
  }

}
