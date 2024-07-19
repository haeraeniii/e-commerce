package hhplus.e_commerce.domain.cart.service.dto;

public class CartCommand {
    public record Create (
            long id,
            long customerId,
            long orderQuantity
    ) {}
}
