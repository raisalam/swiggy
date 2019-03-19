package com.rais.swiggy.order.domain;



import com.rais.swiggy.common.events.OrderCreatedEvent;
import com.rais.swiggy.common.events.OrderEvent;
import io.eventuate.tram.events.ResultWithEvents;
import io.eventuate.tram.events.aggregates.ResultWithDomainEvents;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

@Entity
@Table(name="orders")
@Access(AccessType.FIELD)
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private OrderState state;

  @Embedded
  private OrderDetails orderDetails;

  public Order() {
  }

  public Order(OrderDetails orderDetails) {
    this.orderDetails = orderDetails;
    this.state = OrderState.PENDING;
  }

  public static ResultWithDomainEvents<Order,OrderEvent> createOrder(OrderDetails orderDetails) {
    Order order = new Order(orderDetails);
    com.rais.swiggy.common.dtos.OrderDetails orderDetail  = new com.rais.swiggy.common.dtos.OrderDetails(orderDetails.getCustomerId(), orderDetails.getOrderTotal());
    List<OrderEvent> events = singletonList(new OrderCreatedEvent(orderDetail));
    return new ResultWithDomainEvents<>(order, events);
  }

  public Long getId() {
    return id;
  }

  public void noteCreditReserved() {
    this.state = OrderState.APPROVED;
  }

  public void noteCreditReservationFailed() {
    this.state = OrderState.REJECTED;
  }

  public OrderState getState() {
    return state;
  }
}
