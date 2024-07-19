package hhplus.e_commerce.domain.product.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class ProductDto {
    private long id;

    private String title;

    private long price;

    private List<ProductOptionDto> productOptionDtoList;
}
