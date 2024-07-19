package hhplus.e_commerce.domain.cart.controller.dto;

import hhplus.e_commerce.domain.cart.service.dto.CartCommand;

public record CartRequestDto(long id, long customerId, long orderQuantity) {
    public CartCommand.Create toCartCreateCommand () {
        return new CartCommand.Create(id, customerId, orderQuantity);
    }
}
