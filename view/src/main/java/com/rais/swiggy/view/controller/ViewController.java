package com.rais.swiggy.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rais.swiggy.view.domain.Customer;
import com.rais.swiggy.view.domain.Order;
import com.rais.swiggy.view.response.CustomerResponse;
import com.rais.swiggy.view.response.OrderResponse;
import com.rais.swiggy.view.service.ViewService;

@RestController
public class ViewController {

	@Autowired
	ViewService viewService;

	@RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET)
	public ResponseEntity<CustomerResponse> getCustomer(
			@PathVariable Long customerId) {

		Customer customer = viewService.getCustomerDetail(customerId).get();
		if (customer == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(new CustomerResponse(customer.getId(),
					customer.getName()), HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
	public ResponseEntity<OrderResponse> getOrder(
			@PathVariable Long orderId) {

		Order order = viewService.getOrderDetail(orderId).get();
		if (order == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(new OrderResponse(order.getId(),
					order.getState()), HttpStatus.OK);
		}
	}
}
