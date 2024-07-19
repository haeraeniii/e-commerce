package hhplus.e_commerce.domain.customer.controller;

import hhplus.e_commerce.domain.customer.controller.dto.CustomerRequestDto;
import hhplus.e_commerce.domain.customer.controller.dto.CustomerResponseDto;
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

}
