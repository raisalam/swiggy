package com.rais.swiggy.common.events;

import com.rais.swiggy.common.domain.Money;
import com.rais.swiggy.common.dtos.OrderDetails;

public class OrderRejectedEvent implements OrderEvent {

    @Override
    public Long getCustomerId() {
        return 1L;
    }

    @Override
    public Money getOrderTotal() {
        return new Money();
    }
}
