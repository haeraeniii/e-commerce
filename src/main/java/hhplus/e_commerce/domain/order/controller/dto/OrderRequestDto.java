package hhplus.e_commerce.domain.order.controller.dto;

import hhplus.e_commerce.domain.order.service.command.OrderCommand;

import java.util.List;

public record OrderRequestDto (long customerId, List<OrderItemRequestDto> orderItemRequestDtoList) {
    public OrderCommand.Create toOrderCreateCommand() {
        return new OrderCommand.Create(
                customerId,
                orderItemRequestDtoList.stream()
                        .map(it -> new OrderCommand.Create.NewOrderItem(
                                it.productId(),
                                it.productOptionId(),
                                it.orderQuantity()
                        )).toList()
        );
    }
}
