package com.rais.swiggy.common.events;

import io.eventuate.tram.events.common.DomainEvent;

public interface CustomerEvent extends DomainEvent {

   String getCustomerName();
}
