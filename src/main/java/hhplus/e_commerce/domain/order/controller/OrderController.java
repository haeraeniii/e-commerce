package hhplus.e_commerce.domain.order.controller;

import hhplus.e_commerce.base.data.ApiOneResult;
import hhplus.e_commerce.base.exception.CustomException;
import hhplus.e_commerce.domain.order.controller.dto.OrderRequestDto;
import hhplus.e_commerce.domain.order.controller.dto.OrderResponseDto;
import hhplus.e_commerce.domain.order.controller.dto.mapper.OrderMapper;
import hhplus.e_commerce.domain.order.controller.dto.mapper.OrderSheetMapper;
import hhplus.e_commerce.domain.order.entity.Order;
import hhplus.e_commerce.domain.order.entity.OrderSheet;
import hhplus.e_commerce.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {


}
