package hhplus.e_commerce.domain.cart.controller.dto;

import hhplus.e_commerce.domain.product.controller.dto.ProductOptionResponseDto;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public record CartResponseDto(long id, List<ProductOptionResponseDto> productOptionResponseList) {
}
