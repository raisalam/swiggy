package com.rais.swiggy.order.service;

import com.rais.swiggy.order.api.reply.ApproveOrderCommand;
import com.rais.swiggy.order.api.reply.RejectOrderCommand;
import com.rais.swiggy.order.domain.Order;
import com.rais.swiggy.order.domain.OrderRepository;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

public class OrderCommandHandler {

  @Autowired
  private OrderRepository orderRepository;

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
    return withSuccess();
  }

}
