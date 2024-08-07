package hhplus.e_commerce.domain.customer.service.repository;

import hhplus.e_commerce.domain.customer.entity.Customer;

public interface CustomerRepository {

    Customer getCustomer (long customerId);

    Customer findByName (String name);


    Customer findByIdWithPessimisticWriteLock(long customerId);

    Customer getBalance(long customerId);

    Customer save(Customer customer);
}
