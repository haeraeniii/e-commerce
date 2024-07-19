package hhplus.e_commerce.domain.customer.controller;

import hhplus.e_commerce.base.exception.CustomException;
import hhplus.e_commerce.domain.customer.controller.dto.CustomerRequestDto;
import hhplus.e_commerce.domain.customer.controller.dto.CustomerResponseDto;
import hhplus.e_commerce.domain.customer.controller.dto.mapper.CustomerMapper;
import hhplus.e_commerce.base.data.ApiOneResult;
import hhplus.e_commerce.domain.customer.entity.Customer;
import hhplus.e_commerce.domain.customer.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    private final CustomerMapper customerMapper;

    @Autowired
    private HttpSession session;

    /**
     * 1. 고객 등록
     * 2. 고객 잔액 조회
     * 3. 고객 잔액 충전
     */

    @Transactional
    @PostMapping("/register")
    public ApiOneResult<CustomerResponseDto> registerCustomer (@RequestBody String name) throws CustomException {
        Customer customer = customerService.registerCustomer(name);

        session.setAttribute("userId", customer.getId());

        System.out.println("userId" + session.getAttribute("userId"));

        return new ApiOneResult<>(customerMapper.toDto(customer));
    }

    @GetMapping("/checkBalance")
    public ApiOneResult<Long> checkBalance (@RequestBody long customerId) {
        Customer response = customerService.checkBalance(customerId);

        return new ApiOneResult<>(response.getBalance());
    }

    @Transactional
    @PutMapping("/charge")
    public ApiOneResult<CustomerResponseDto> charge (@RequestBody CustomerRequestDto customerRequestDto) {

        Customer response = customerService.charge(customerRequestDto.toCustomerCommand());

        return new ApiOneResult<>(true, "충전되었습니다.", customerMapper.toDto(response));
    }
}
