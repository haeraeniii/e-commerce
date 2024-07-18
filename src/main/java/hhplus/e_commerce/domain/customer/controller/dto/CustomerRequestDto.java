package hhplus.e_commerce.domain.customer.controller.dto;

import hhplus.e_commerce.domain.customer.service.dto.CustomerCommand;

public record CustomerRequestDto(long customerId, long balance) {
    public CustomerCommand.Create toCustomerCommand() {
        return new CustomerCommand.Create(
                customerId,
                balance
        );
    }
}
