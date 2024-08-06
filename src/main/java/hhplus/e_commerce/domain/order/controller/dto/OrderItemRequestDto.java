package hhplus.e_commerce.domain.order.controller.dto;

public record OrderItemRequestDto(long productId, long productOptionId, long orderQuantity) {
}
