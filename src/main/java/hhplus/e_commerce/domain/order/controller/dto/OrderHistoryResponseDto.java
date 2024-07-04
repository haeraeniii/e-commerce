package hhplus.e_commerce.domain.order.controller.dto;


import java.time.LocalDateTime;
import java.util.List;

public record OrderHistoryResponseDto(long id, String status, LocalDateTime orderDate, List<OrderDetailResponseDto> orderDetailList) {
}
