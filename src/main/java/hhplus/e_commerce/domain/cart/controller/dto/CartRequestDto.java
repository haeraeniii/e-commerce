package hhplus.e_commerce.domain.cart.controller.dto;

import lombok.Getter;
import lombok.ToString;

public record CartRequestDto(long id, long customerId) {
}
