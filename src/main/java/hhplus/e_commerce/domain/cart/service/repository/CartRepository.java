package hhplus.e_commerce.domain.cart.service.repository;

import hhplus.e_commerce.domain.cart.entity.Cart;

import java.util.List;

public interface CartRepository {
    List<Cart> getCartList();

    Cart getCart(long id);

    void addCart(Cart cart);

    void deleteCart(long id);

    void deleteAll(List<Cart> carts);
}
