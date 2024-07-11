package hhplus.e_commerce.domain.cart.controller;

import hhplus.e_commerce.base.data.ApiOneResult;
import hhplus.e_commerce.base.data.ApiResult;
import hhplus.e_commerce.domain.cart.controller.dto.CartDto;
import hhplus.e_commerce.domain.cart.controller.dto.CartRequestDto;
import hhplus.e_commerce.domain.cart.controller.dto.mapper.CartMapper;
import hhplus.e_commerce.domain.cart.entity.Cart;
import hhplus.e_commerce.domain.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    private final CartMapper cartMapper;

    /**
     * 1. 장바구니 조회
     * 2. 장바구니 상품 담기
     * 3. 장바구니 상품 삭제
     * 4. 장바구니 비우기
     */

    @GetMapping("/{customerId}")
    public ApiOneResult<List<CartDto>> cart (@PathVariable long customerId) {

        List<Cart> cartList = cartService.getCartList(customerId);

        List<CartDto> sortedList = new ArrayList<>();

        cartList.forEach(cart -> sortedList.add(cartMapper.toDto(cart)));

        return new ApiOneResult<>(true, "", sortedList);
    }

    @PostMapping("/add")
    public ApiResult addCart (@RequestBody CartDto cartDto) {

        Cart entity = cartMapper.toEntity(cartDto);

        cartService.addCart(entity);

        return ApiResult.success("장바구니에 추가되었습니다.");
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult deleteCart (@PathVariable long id) {

        cartService.deleteCart(id);

        return ApiResult.success("장바구니에서 삭제되었습니다.");
    }

    @DeleteMapping("/deleteAll/{customerId}")
    public ApiResult deleteAllCart (@PathVariable long customerId) {

        cartService.deleteAll(customerId);

        return ApiResult.success("장바구니를 모두 비웠습니다.");
    }
}
