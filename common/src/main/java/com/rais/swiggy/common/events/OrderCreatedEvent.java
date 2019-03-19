package com.rais.swiggy.common.events;

import com.rais.swiggy.common.domain.Money;
import com.rais.swiggy.common.dtos.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent implements OrderEvent {

   OrderDetails orderDetails;

    @Override
    public Long getCustomerId() {
        return orderDetails.getCustomerId();
    }

    @Override
    public Money getOrderTotal() {
        return orderDetails.getOrderTotal();
    }
}
