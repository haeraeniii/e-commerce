package hhplus.e_commerce.domain.order.controller;

import hhplus.e_commerce.base.data.ApiOneResult;
import hhplus.e_commerce.domain.order.controller.dto.OrderDto;
import hhplus.e_commerce.domain.order.controller.dto.mapper.OrderMapper;
import hhplus.e_commerce.domain.order.entity.Order;
import hhplus.e_commerce.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final OrderMapper mapper;

    /**
     * 1. 상품 주문
     * 2. 주문한 내역 보기
     */

    //상품 주문
    @PostMapping("/")
    public ApiOneResult<OrderDto> order (@RequestBody OrderDto orderDto) {
        Order order = mapper.toOrder(orderDto);

        Order response = orderService.order(order);

        return new ApiOneResult<>(true, "주문되었습니다.", mapper.toOrderDto(response));
    }

    // 주문 내역 보기
    @GetMapping("/orderedList")
    public ApiOneResult<List<OrderDto>> getOrderedList () {

        List<Order> response = orderService.getOrderList();

        List<OrderDto> orderDtoList = new ArrayList<>();

        response.forEach(order -> orderDtoList.add(mapper.toOrderDto(order)));

        return new ApiOneResult<>(orderDtoList);
    }
}
