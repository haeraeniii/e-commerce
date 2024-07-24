package hhplus.e_commerce.domain.cart.service;

import hhplus.e_commerce.domain.cart.entity.Cart;
import hhplus.e_commerce.domain.cart.service.command.CartCommand;
import hhplus.e_commerce.domain.cart.service.repository.CartRepository;
import hhplus.e_commerce.domain.product.entity.ProductOption;
import hhplus.e_commerce.domain.product.service.repository.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductOptionRepository productOptionRepository;

    public List<Cart> getCartList(long customerId) {
        return cartRepository.getAllByCustomerId(customerId);
    }

    public void addCart(CartCommand.Create command) {
        ProductOption productOption = productOptionRepository.getById(command.id());

        Cart cart = Cart.builder()
                .customerId(command.customerId())
                .productOptionId(productOption.getId())
                .productName(productOption.getProduct().getTitle())
                .color(productOption.getColor())
                .size(productOption.getSize())
                .price(productOption.getPrice())
                .orderQuantity(command.orderQuantity()).build();

        cartRepository.addCart(cart);
    }

    public void deleteCart(long id) {
        cartRepository.deleteCart(id);
    }

    public void deleteAll(long customerId) {
        cartRepository.deleteAll(customerId);
    }
}
