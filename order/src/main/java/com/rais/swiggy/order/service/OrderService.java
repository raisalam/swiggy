package com.rais.swiggy.order.service;

import com.rais.swiggy.common.events.OrderEvent;
import com.rais.swiggy.order.domain.Order;
import com.rais.swiggy.order.domain.OrderDetails;
import com.rais.swiggy.order.domain.OrderDomainEventPublisher;
import com.rais.swiggy.order.domain.OrderRepository;
import com.rais.swiggy.order.saga.CreateOrderSagaData;
import io.eventuate.tram.events.ResultWithEvents;
import io.eventuate.tram.events.aggregates.ResultWithDomainEvents;
import io.eventuate.tram.sagas.orchestration.SagaManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class OrderService {

  @Autowired
  private SagaManager<CreateOrderSagaData> createOrderSagaManager;

  @Autowired
  private OrderDomainEventPublisher orderAggregateEventPublisher;

  @Autowired
  private OrderRepository orderRepository;

  @Transactional
  public Order createOrder(OrderDetails orderDetails) {
    ResultWithDomainEvents<Order, OrderEvent> orderAndEvents = Order.createOrder(orderDetails);
    Order order = orderAndEvents.result;
    orderRepository.save(order);
    orderAggregateEventPublisher.publish(order, orderAndEvents.events);
    CreateOrderSagaData data = new CreateOrderSagaData(order.getId(), orderDetails);
    createOrderSagaManager.create(data, Order.class, order.getId());
    return order;
  }

}
