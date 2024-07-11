package hhplus.e_commerce.domain.customer.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class CustomerDto {
    private long id;
    private long balance;
    private OrderSheetDto orderSheetDto;
}
