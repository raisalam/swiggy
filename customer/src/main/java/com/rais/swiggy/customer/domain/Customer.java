package com.rais.swiggy.customer.domain;

import static java.util.Collections.singletonList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rais.swiggy.common.domain.Money;
import com.rais.swiggy.common.events.CustomerCreatedEvent;
import com.rais.swiggy.common.events.CustomerEvent;

import io.eventuate.tram.events.aggregates.ResultWithDomainEvents;

@Entity
@Table(name = "Customer")
@Access(AccessType.FIELD)
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@Embedded
	private Money creditLimit;

	@ElementCollection
	private Map<Long, Money> creditReservations;

	Money availableCredit() {
		return creditLimit.subtract(creditReservations.values().stream().reduce(Money.ZERO, Money::add));
	}

	public Customer() {
	}

	public Customer(String name, Money creditLimit) {
		this.name = name;
		this.creditLimit = creditLimit;
		this.creditReservations = Collections.emptyMap();
	}

	public static ResultWithDomainEvents<Customer, CustomerEvent> createCustomer(String customerName,
			Money creditLimit) {
		Customer customer = new Customer(customerName, creditLimit);
		List<CustomerEvent> events = singletonList(new CustomerCreatedEvent(customerName));
		return new ResultWithDomainEvents<>(customer, events);
	}

	public Long getId() {
		return id;
	}

	public void reserveCredit(Long orderId, Money orderTotal) {
		if (availableCredit().isGreaterThanOrEqual(orderTotal)) {
			creditReservations.put(orderId, orderTotal);
		} else
			throw new CustomerCreditLimitExceededException();
	}
}
