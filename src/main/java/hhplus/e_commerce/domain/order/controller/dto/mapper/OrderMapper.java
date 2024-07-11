package hhplus.e_commerce.domain.order.controller.dto.mapper;

import hhplus.e_commerce.domain.order.controller.dto.OrderDto;
import hhplus.e_commerce.domain.order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "orderItemList", source = "orderItemDtoList")
    Order toOrder(OrderDto dto);

    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "orderItemDtoList", source = "orderItemList")
    OrderDto toOrderDto(Order order);
}
