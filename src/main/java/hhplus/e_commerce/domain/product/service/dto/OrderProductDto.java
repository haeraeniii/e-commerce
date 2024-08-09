package hhplus.e_commerce.domain.product.service.dto;

import hhplus.e_commerce.domain.product.service.command.OrderProductCommand;

import java.util.List;

public record OrderProductDto(long totalAmount, List<OrderOptionAndQuantityDto> orderOptionAndQuantityList){
    public OrderProductCommand.Create toCommand() {
        return new OrderProductCommand.Create(
          totalAmount,
          orderOptionAndQuantityList.stream()
              .map(it -> new OrderProductCommand.Create.OrderProductOption(
                      it.option().getProduct().getId(),
                      it.option().getId(),
                      it.option().getProduct().getTitle(),
                      it.option().getColor(),
                      it.option().getSize(),
                      it.option().getPrice(),
                      it.orderQuantity()
              )).toList()
        );
    }
}
