package hhplus.e_commerce.domain.customer.service.dto;

public class CustomerCommand {

    public record Create (
        long customerId,
        long balance
    ) {}
}
