package hhplus.e_commerce.domain.order.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class OrderDto {
    private long customerId;
    private List<OrderItemDto> orderItemDtoList;
    private LocalDateTime createAt;
}
