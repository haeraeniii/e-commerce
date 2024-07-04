package hhplus.e_commerce.domain.cart.controller;

import hhplus.e_commerce.data.ApiOneResult;
import hhplus.e_commerce.data.ApiResult;
import hhplus.e_commerce.domain.cart.controller.dto.CartRequestDto;
import hhplus.e_commerce.domain.cart.controller.dto.CartResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    /**
     * 1. 장바구니 조회
     * 2. 장바구니 상품 담기
     * 3. 장바구니 상품 삭제
     * 4. 장바구니 비우기
     */

    @GetMapping("/")
    public ApiOneResult<CartResponseDto> cart () {

        return new ApiOneResult<>(new CartResponseDto(0, new ArrayList<>()));
    }

    @PostMapping("/add")
    public ApiResult addCart (@RequestBody CartRequestDto cartRequestDto) {

        return ApiResult.success("장바구니에 추가되었습니다.");
    }

    @DeleteMapping("/delete")
    public ApiResult deleteCart (@RequestBody long id) {

        return ApiResult.success("장바구니에서 삭제되었습니다.");
    }

    @DeleteMapping("/deleteAll")
    public ApiResult deleteAllCart (@RequestBody long customerId) {

        return ApiResult.success("장바구니를 모두 비웠습니다.");
    }
}
