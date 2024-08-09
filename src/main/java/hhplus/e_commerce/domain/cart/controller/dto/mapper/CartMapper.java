package hhplus.e_commerce.domain.cart.controller.dto.mapper;

import hhplus.e_commerce.domain.cart.controller.dto.CartResponseDto;
import hhplus.e_commerce.domain.cart.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    CartResponseDto toDto(Cart cart);
}
