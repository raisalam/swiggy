package com.rais.swiggy.customer.service;

import com.rais.swiggy.common.domain.Money;
import com.rais.swiggy.customer.domain.Customer;
import com.rais.swiggy.customer.domain.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  public Customer createCustomer(String name, Money creditLimit) {
    Customer customer  = new Customer(name, creditLimit);
    System.out.println("CustomerService :: createCustomer()");
    return customerRepository.save(customer);
  }
}
