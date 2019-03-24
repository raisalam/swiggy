package com.rais.swiggy.view.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Customer")
@Access(AccessType.FIELD)
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  public Customer() {
  }

  public Customer(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }
  
  public String getName() {
	    return name;
	  }

}
