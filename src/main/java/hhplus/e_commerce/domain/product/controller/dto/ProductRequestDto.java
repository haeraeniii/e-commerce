package hhplus.e_commerce.domain.product.controller.dto;

import hhplus.e_commerce.domain.product.service.dto.ProductCommand;

import java.util.List;

public record ProductRequestDto(String title, long price, List<ProductOptionRequestDto> productOptionRequestDtoList){
    public ProductCommand.Create toProductCreateCommand() {
        return new ProductCommand.Create(
           title,
           price,
           productOptionRequestDtoList.stream()
                   .map(it -> new ProductCommand.Create.NewProductOption(
                           it.color(),
                           it.size(),
                           it.stock()
                   )).toList()
        );
    }
}
