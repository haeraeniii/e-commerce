package hhplus.e_commerce.domain.customer.service.repository;

import hhplus.e_commerce.domain.customer.entity.Customer;

import java.util.Optional;

public interface CustomerRepository {

    Customer getCustomer (long customerId);
    Customer getBalance(long customerId);

    Customer save(Customer customer);
}
