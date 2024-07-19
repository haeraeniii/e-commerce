package hhplus.e_commerce.domain.order.controller.dto;

public record OrderItemResponseDto(
        long id,
        String productName,
        String color,
        String size,
        long price,
        long orderQuantity
) {
}
