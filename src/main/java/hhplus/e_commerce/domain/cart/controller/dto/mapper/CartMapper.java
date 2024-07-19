package hhplus.e_commerce.domain.cart.controller.dto.mapper;

import hhplus.e_commerce.domain.cart.controller.dto.CartDto;
import hhplus.e_commerce.domain.cart.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    @Mapping(target = "id", ignore = true)
    Cart toEntity(CartDto cartDto);

    CartDto toDto(Cart cart);
}
