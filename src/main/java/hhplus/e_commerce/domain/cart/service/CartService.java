package hhplus.e_commerce.domain.cart.service;

import hhplus.e_commerce.domain.cart.entity.Cart;
import hhplus.e_commerce.domain.cart.service.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public List<Cart> getCartList(long customerId) {

        return getSortedList(customerId);
    }

    public void addCart(Cart cart) {
        cartRepository.addCart(cart);
    }

    public void deleteCart(long id) {
        cartRepository.deleteCart(id);
    }

    public void deleteAll(long customerId) {
        List<Cart> sortedList = getSortedList(customerId);

        cartRepository.deleteAll(sortedList);
    }

    //특정 고객에 대한 장바구니 리스트
    List<Cart> getSortedList (long customerId) {
        List<Cart> cartList = cartRepository.getCartList();

        List<Cart> sortedList = new ArrayList<>();
        cartList.forEach(cart -> {
            if(cart.getCustomerId() == customerId) sortedList.add(cart);
        });

        return sortedList;
    }
}
