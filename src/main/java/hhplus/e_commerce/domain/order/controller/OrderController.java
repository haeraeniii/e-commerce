package hhplus.e_commerce.domain.order.controller;

import hhplus.e_commerce.data.ApiOneResult;
import hhplus.e_commerce.data.ApiResult;
import hhplus.e_commerce.domain.order.controller.dto.OrderHistoryResponseDto;
import hhplus.e_commerce.domain.order.controller.dto.OrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    /**
     * 1. 주문 내역 보기
     * 2. 상품 주문
     * 3. 상품 주문 취소
     * 4. 주문한 상품 환불
     */

    @PostMapping("/")
    public ApiResult order () {

        return ApiResult.success("주문되었습니다.");
    }

    @PostMapping("/cancel")
    public ApiResult orderCancel (@RequestBody OrderRequestDto orderRequestDto) {

        return ApiResult.success("주문이 취소되었습니다.");
    }

    @PostMapping("refund")
    public ApiResult refund (@RequestBody long id) {

        return ApiResult.success("상품이 환불되었습니다.");
    }

    @GetMapping("/orderedList/{customerId}")
    public ApiOneResult<OrderHistoryResponseDto> showOrderedList (@PathVariable long customerId) {

        return new ApiOneResult<>(new OrderHistoryResponseDto(0, "결제 완료", LocalDateTime.now(), new ArrayList<>()));
    }
}
