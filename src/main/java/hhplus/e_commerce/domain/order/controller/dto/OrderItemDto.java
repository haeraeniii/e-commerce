package hhplus.e_commerce.domain.order.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class OrderItemDto {
    private long productOptionId;
    private String productName;
    private String color;
    private String size;
    private long price;
    private long orderQuantity;
}
