package hhplus.e_commerce.domain.cart.repositoryImpl.repository;

import hhplus.e_commerce.domain.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartJpaRepository extends JpaRepository<Cart, Long> {

    List<Cart> findAllByCustomerId(long customerId);
    void deleteAllByCustomerId(long id);
}
