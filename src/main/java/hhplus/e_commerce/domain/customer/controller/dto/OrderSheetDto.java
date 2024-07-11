package hhplus.e_commerce.domain.customer.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class OrderSheetDto {
    private long productOptionId;

    private String title;

    private String color;

    private String size;

    private long price;
}
