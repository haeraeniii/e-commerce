package hhplus.e_commerce.domain.cart.repositoryImpl;

import hhplus.e_commerce.domain.cart.entity.Cart;
import hhplus.e_commerce.domain.cart.repositoryImpl.repository.CartJpaRepository;
import hhplus.e_commerce.domain.cart.service.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepository {
    private final CartJpaRepository cartJpaRepository;

    @Override
    public List<Cart> getCartList() {
        return cartJpaRepository.findAll();
    }

    @Override
    public Cart getCart(long id) {
        return cartJpaRepository.getReferenceById(id);
    }

    @Override
    public void addCart(Cart cart) {
        cartJpaRepository.save(cart);
    }

    @Override
    public void deleteCart(long id) {
        cartJpaRepository.deleteById(id);
    }

    public void deleteAll(List<Cart> carts) {
        cartJpaRepository.deleteAll(carts);
    }
}
