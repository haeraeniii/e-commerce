package hhplus.e_commerce.domain.product.controller.dto.mapper;

import hhplus.e_commerce.domain.product.controller.dto.ProductDto;
import hhplus.e_commerce.domain.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productOptionList", source = "productOptionDtoList")
    Product toEntity(ProductDto productDto);


    @Mapping(target = "productOptionDtoList", source = "productOptionList")
    ProductDto toDto(Product product);
}
