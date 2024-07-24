package hhplus.e_commerce.domain.cart.service;

import hhplus.e_commerce.domain.cart.entity.Cart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {
    @InjectMocks
    private CartService cartService;

    @Test
    @DisplayName("해당 고객의 장바구니 리스트를 조회한다.")
    public void getCartListTest() {
        //given
        Cart cart1 = Cart.builder().customerId(1).productOptionId(1).build();

        Cart cart2 = Cart.builder().customerId(1).productOptionId(2).build();

        Cart cart3 = Cart.builder().customerId(1).productOptionId(3).build();

        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart1);
        cartList.add(cart2);
        cartList.add(cart3);

        List<Cart> sortedList = new ArrayList<>();

        cartList.forEach(cart -> {
            if(cart.getCustomerId() == 1) sortedList.add(cart);
        });

        //when
        when(cartService.getCartList(1)).thenReturn(sortedList);
        List<Cart> carts = cartService.getCartList(1);

        //then
        assertThat(carts).isEqualTo(sortedList);
    }
}