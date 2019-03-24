package com.rais.swiggy.view.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.rais.swiggy.view.domain.Customer;
import com.rais.swiggy.view.domain.CustomerRepository;
import com.rais.swiggy.view.domain.Order;
import com.rais.swiggy.view.domain.OrderDetails;
import com.rais.swiggy.view.domain.OrderRepository;

public class ViewService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public Order saveOrder(OrderDetails orderDetails) {
		Order order = new Order(orderDetails);
		orderRepository.save(order);
		System.out.println("ViewService :: saveOrder()");
		
		return order;
	}
	
	public Customer saveCustomer(String name) {
		Customer cust = new Customer(name);
		customerRepository.save(cust);
		System.out.println("ViewService :: createCustomer()");
		
		return cust;
	}
	
	public Optional<Customer> getCustomerDetail(Long customerId) {
		Optional<Customer> customer =  customerRepository.findById(customerId);
		System.out.println("ViewService :: getCustomerDetail()");
		
		return customer;
	}
	
	public Optional<Order> getOrderDetail(Long orderId) {
		Optional<Order> order =  orderRepository.findById(orderId);
		System.out.println("ViewService :: getOrderDetail()");
		
		return order;
	}
}
