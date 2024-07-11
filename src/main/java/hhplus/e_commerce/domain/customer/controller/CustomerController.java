package hhplus.e_commerce.domain.customer.controller;

import hhplus.e_commerce.domain.customer.controller.dto.CustomerDto;
import hhplus.e_commerce.domain.customer.controller.dto.mapper.CustomerMapper;
import hhplus.e_commerce.base.data.ApiOneResult;
import hhplus.e_commerce.base.data.ApiResult;
import hhplus.e_commerce.domain.customer.entity.Customer;
import hhplus.e_commerce.domain.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    private final CustomerMapper customerMapper;

    /**
     * 1. 고객 잔액 조회
     * 2. 고객 잔액 충전
     */

    @GetMapping("/checkBalance")
    public ApiOneResult<Long> checkBalance (@RequestBody long customerId) {
        Customer response = customerService.checkBalance(customerId);

        return new ApiOneResult<>(response.getBalance());
    }

    @PutMapping("/charge")
    public ApiResult charge (@RequestBody CustomerDto customerDto) {
        Customer entity = customerMapper.toEntity(customerDto);

        customerService.charge(entity);

        return ApiResult.success("금액이 충전되었습니다.");
    }
}
