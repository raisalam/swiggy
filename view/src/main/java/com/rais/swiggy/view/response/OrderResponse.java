package com.rais.swiggy.view.response;

import lombok.Data;

import com.rais.swiggy.view.domain.OrderState;

@Data
public class OrderResponse {
  private Long orderId;
  private OrderState orderState;

  public OrderResponse() {
  }

  public OrderResponse(Long orderId, OrderState orderState) {
    this.orderId = orderId;
    this.orderState = orderState;
  }
  
}
