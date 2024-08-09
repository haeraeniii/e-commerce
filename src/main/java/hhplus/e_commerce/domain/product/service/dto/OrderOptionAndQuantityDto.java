package hhplus.e_commerce.domain.product.service.dto;

import hhplus.e_commerce.domain.product.entity.ProductOption;

public record OrderOptionAndQuantityDto(
        ProductOption option,
        long orderQuantity
){
}
