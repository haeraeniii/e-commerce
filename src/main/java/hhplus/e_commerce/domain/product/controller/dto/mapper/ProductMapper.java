package hhplus.e_commerce.domain.product.controller.dto.mapper;

import hhplus.e_commerce.domain.product.controller.dto.ProductResponseDto;
import hhplus.e_commerce.domain.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);


    @Mapping(target = "productOptionDtoList", source = "productOptionList")
    ProductResponseDto toDto(Product product);
}
