package hhplus.e_commerce.domain.product.controller.dto;

import java.util.List;

public record ProductResponseDto(long id, String title, long price, List<ProductOptionResponseDto> ProductOptionList) {
}
