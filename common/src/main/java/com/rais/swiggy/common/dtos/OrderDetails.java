package com.rais.swiggy.common.dtos;

import com.rais.swiggy.common.domain.Money;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class OrderDetails {

    private Long customerId;

    private Money orderTotal;
}

