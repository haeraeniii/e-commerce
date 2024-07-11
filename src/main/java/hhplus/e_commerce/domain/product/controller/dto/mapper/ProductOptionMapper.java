package hhplus.e_commerce.domain.product.controller.dto.mapper;

import hhplus.e_commerce.domain.product.controller.dto.ProductOptionDto;
import hhplus.e_commerce.domain.product.entity.ProductOption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductOptionMapper {
    ProductOptionMapper INSTANCE = Mappers.getMapper(ProductOptionMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    ProductOption toEntity(ProductOptionDto productOptionDto);

    ProductOptionDto toDto(ProductOption productOption);
}
