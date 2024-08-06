package hhplus.e_commerce.domain.order.controller.dto.mapper;

import hhplus.e_commerce.domain.order.controller.dto.OrderResponseDto;
import hhplus.e_commerce.domain.order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);


    @Mapping(target = "orderItemResponseDtoList", source = "orderItemList")
    OrderResponseDto toDto(Order order);
}
