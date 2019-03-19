package com.rais.swiggy.common.events;

import com.rais.swiggy.common.domain.Money;
import io.eventuate.tram.events.common.DomainEvent;

public interface OrderEvent extends DomainEvent {

   Long getCustomerId();
   Money getOrderTotal();

}
