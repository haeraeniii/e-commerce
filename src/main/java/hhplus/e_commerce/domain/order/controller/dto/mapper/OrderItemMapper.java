package hhplus.e_commerce.domain.order.controller.dto.mapper;

import hhplus.e_commerce.domain.order.controller.dto.OrderItemDto;
import hhplus.e_commerce.domain.order.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);


    @Mapping(target = "id", constant = "0L")
    @Mapping(target = "order", ignore = true)
    OrderItem toOrderItem(OrderItemDto orderItemDto);

    OrderItemDto toDto(OrderItem orderItem);
}
