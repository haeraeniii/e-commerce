package hhplus.e_commerce.domain.cart.service.command;

public class CartCommand {
    public record Create (
            long id,
            long customerId,
            long orderQuantity
    ) {}
}
