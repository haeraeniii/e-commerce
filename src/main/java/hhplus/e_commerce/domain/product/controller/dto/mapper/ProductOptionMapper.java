package hhplus.e_commerce.domain.product.controller.dto.mapper;

import hhplus.e_commerce.domain.product.controller.dto.ProductOptionDto;
import hhplus.e_commerce.domain.product.entity.ProductOption;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductOptionMapper {
    ProductOptionMapper INSTANCE = Mappers.getMapper(ProductOptionMapper.class);

    ProductOptionDto toDto(ProductOption productOption);
}
