package hhplus.e_commerce.domain.customer.service.command;

public class CustomerCommand {

    public record Create (
        long customerId,
        long balance
    ) {}
}
