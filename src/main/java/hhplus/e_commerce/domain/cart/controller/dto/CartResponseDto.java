package hhplus.e_commerce.domain.cart.controller.dto;
public record CartResponseDto (
    long id,
    String productName,
    String color,
    String size,
    long price,

    long orderQuantity
){}
