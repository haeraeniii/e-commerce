package hhplus.e_commerce.domain.product.controller.dto;

import hhplus.e_commerce.domain.product.service.command.ProductCommand;

import java.util.List;

public record ProductRequestDto(String title, List<ProductOptionRequestDto> productOptionRequestDtoList){
    public ProductCommand.Create toProductCreateCommand() {
        return new ProductCommand.Create(
           title,
           productOptionRequestDtoList.stream()
                   .map(it -> new ProductCommand.Create.NewProductOption(
                           it.color(),
                           it.size(),
                           it.stock(),
                           it.price()
                   )).toList()
        );
    }
}
