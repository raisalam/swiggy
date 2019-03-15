package com.rais.swiggy.customer.api;

import com.rais.swiggy.common.domain.Money;

public class CreateCustomerRequest {
  private String name;
  private Money creditLimit;

  public CreateCustomerRequest() {
  }

  public CreateCustomerRequest(String name, Money creditLimit) {

    this.name = name;
    this.creditLimit = creditLimit;
  }


  public String getName() {
    return name;
  }

  public Money getCreditLimit() {
    return creditLimit;
  }
}
