package hhplus.e_commerce.domain.order.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record OrderResponseDto (long customerId, List<OrderItemResponseDto> orderItemResponseDtoList, LocalDateTime createdAt) {

}
