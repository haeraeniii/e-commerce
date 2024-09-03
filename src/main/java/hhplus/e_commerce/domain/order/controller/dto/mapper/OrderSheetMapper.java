package hhplus.e_commerce.domain.order.controller.dto.mapper;

import hhplus.e_commerce.domain.order.controller.dto.OrderResponseDto;
import hhplus.e_commerce.domain.order.entity.OrderSheet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderSheetMapper {
    OrderSheetMapper INSTANCE = Mappers.getMapper(OrderSheetMapper.class);

    @Mapping(target = "orderItemResponseDtoList", source = "orderItemSheetList")
    OrderResponseDto toDto(OrderSheet orderSheet);
}
