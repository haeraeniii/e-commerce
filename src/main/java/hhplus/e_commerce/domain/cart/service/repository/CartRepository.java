package hhplus.e_commerce.domain.cart.service.repository;

import hhplus.e_commerce.domain.cart.entity.Cart;

import java.util.List;

public interface CartRepository {
    List<Cart> getAllByCustomerId(long customerId);

    Cart getCart(long id);

    void addCart(Cart cart);

    void deleteCart(long id);

    void deleteAll(long customerId);
}
