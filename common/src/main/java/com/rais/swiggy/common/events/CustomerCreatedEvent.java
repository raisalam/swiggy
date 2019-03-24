package com.rais.swiggy.common.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreatedEvent implements CustomerEvent {

	String customerName;

    @Override
    public String getCustomerName() {
        return customerName;
    }

}
