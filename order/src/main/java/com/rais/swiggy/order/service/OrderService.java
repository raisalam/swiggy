package com.rais.swiggy.order.service;

import com.rais.swiggy.order.domain.Order;
import com.rais.swiggy.order.domain.OrderDetails;
import com.rais.swiggy.order.domain.OrderRepository;
import com.rais.swiggy.order.saga.CreateOrderSagaData;
import io.eventuate.tram.events.ResultWithEvents;
import io.eventuate.tram.sagas.orchestration.SagaManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class OrderService {

  @Autowired
  private SagaManager<CreateOrderSagaData> createOrderSagaManager;

  @Autowired
  private OrderRepository orderRepository;

  @Transactional
  public Order createOrder(OrderDetails orderDetails) {
    ResultWithEvents<Order> oe = Order.createOrder(orderDetails);
    Order order = oe.result;
    orderRepository.save(order);
    CreateOrderSagaData data = new CreateOrderSagaData(order.getId(), orderDetails);
    createOrderSagaManager.create(data, Order.class, order.getId());
    return order;
  }

}
