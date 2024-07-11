package hhplus.e_commerce.domain.product.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class ProductOptionDto {
    private long id;

    private String title;

    private String color;

    private String size;

    private long stock;
}
