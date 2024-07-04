package hhplus.e_commerce.domain.customer.controller;

import hhplus.e_commerce.data.ApiOneResult;
import hhplus.e_commerce.data.ApiResult;
import hhplus.e_commerce.domain.customer.controller.dto.CustomerRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
    /**
     * 1. 잔액 조회
     * 2. 잔액 충전
     */

    @GetMapping("/checkBalance")
    public ApiOneResult<Long> checkBalance (@RequestBody long customerId) {

        return new ApiOneResult<>(300000L);
    }

    @PostMapping("/charge")
    public ApiResult charge (@RequestBody CustomerRequestDto customerRequestDto) {

        return ApiResult.success("금액이 충전되었습니다.");
    }
}
