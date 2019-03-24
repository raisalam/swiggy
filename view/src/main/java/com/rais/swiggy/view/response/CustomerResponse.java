package com.rais.swiggy.view.response;

import lombok.Data;

@Data
public class CustomerResponse {
  private Long customerId;
  private String name;

  public CustomerResponse() {
  }

  public CustomerResponse(Long customerId, String name) {
    this.customerId = customerId;
    this.name = name;
  }
  
}
