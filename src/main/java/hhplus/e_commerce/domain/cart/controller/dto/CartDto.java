package hhplus.e_commerce.domain.cart.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class CartDto {
    private long customerId;

    private long productOptionId;
}
