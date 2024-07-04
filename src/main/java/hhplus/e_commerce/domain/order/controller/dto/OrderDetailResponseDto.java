package hhplus.e_commerce.domain.order.controller.dto;

public record OrderDetailResponseDto(long id, String productName, String color, String size, long price) {
}
