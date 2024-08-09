package hhplus.e_commerce.domain.order.controller;

import hhplus.e_commerce.domain.order.facade.OrderProductFacade;
import hhplus.e_commerce.support.base.data.ApiOneResult;
import hhplus.e_commerce.domain.order.controller.dto.OrderRequestDto;
import hhplus.e_commerce.domain.order.controller.dto.OrderResponseDto;
import hhplus.e_commerce.domain.order.controller.dto.mapper.OrderMapper;
import hhplus.e_commerce.domain.order.controller.dto.mapper.OrderSheetMapper;
import hhplus.e_commerce.domain.order.entity.Order;
import hhplus.e_commerce.domain.order.entity.OrderSheet;
import hhplus.e_commerce.domain.order.service.OrderService;
import hhplus.e_commerce.support.base.data.ApiResult;
import hhplus.e_commerce.support.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final OrderProductFacade orderProductFacade;

    private final OrderMapper mapper;

    private final OrderSheetMapper orderSheetMapper;

    /**
     * 1. 주문서 생성
     * 2. 상품 주문
     * 3. 주문한 내역 보기
     */

    @PostMapping("/test")
    public String test(@RequestHeader(name = "USER_ID") long customerId) {
        return "USER_ID : " + customerId;
    }

    //주문서 생성
    @PostMapping("/createOrderSheet")
    ApiOneResult<OrderResponseDto> createOrderSheet (@RequestBody OrderRequestDto orderRequestDto) {

        OrderSheet response = orderService.createOrderSheet(orderRequestDto.toOrderCreateCommand());

        return new ApiOneResult<>(true, "", orderSheetMapper.toDto(response));
    }

    @PostMapping("/order")
    ApiResult order(@RequestBody OrderRequestDto orderRequestDto) throws CustomException {
        orderProductFacade.order(orderRequestDto.toOrderCreateCommand());

        return new ApiResult(true, "주문되었습니다.");
    }

    // 주문 내역 보기
    @GetMapping("/orderedList")
    public ApiOneResult<List<OrderResponseDto>> getOrderedList () {

        List<Order> response = orderService.getOrderList();

        List<OrderResponseDto> orderDtoList = new ArrayList<>();

        response.forEach(order -> orderDtoList.add(mapper.toDto(order)));

        return new ApiOneResult<>(orderDtoList);
    }
}
