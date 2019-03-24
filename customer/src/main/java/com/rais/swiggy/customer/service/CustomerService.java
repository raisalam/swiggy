package com.rais.swiggy.customer.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.rais.swiggy.common.domain.Money;
import com.rais.swiggy.common.events.CustomerEvent;
import com.rais.swiggy.customer.domain.Customer;
import com.rais.swiggy.customer.domain.CustomerDomainEventPublisher;
import com.rais.swiggy.customer.domain.CustomerRepository;

import io.eventuate.tram.events.aggregates.ResultWithDomainEvents;

public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  CustomerDomainEventPublisher customerDomainEventPublisher;

  public Customer createCustomer(String customerName, Money creditLimit) {
    ResultWithDomainEvents<Customer, CustomerEvent> customerAndEvents = Customer.createCustomer(customerName, creditLimit);
    Customer customer = customerAndEvents.result;
    customerRepository.save(customer);
    System.out.println("CustomerService :: createCustomer()");
    customerDomainEventPublisher.publish(customer, customerAndEvents.events);
    return customer;
  }
}
