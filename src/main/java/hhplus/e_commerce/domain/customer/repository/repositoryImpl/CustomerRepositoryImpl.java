package hhplus.e_commerce.domain.customer.repository.repositoryImpl;

import hhplus.e_commerce.domain.customer.entity.Customer;
import hhplus.e_commerce.domain.customer.repository.CustomerJpaRepository;
import hhplus.e_commerce.domain.customer.service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {
    private final CustomerJpaRepository customerJpaRepository;

    @Override
    public Customer save(Customer customer) {
        return customerJpaRepository.save(customer);
    }

    @Override
    public Customer getCustomer(long customerId) {
        return customerJpaRepository.getReferenceById(customerId);
    }

    @Override
    public Customer findByName(String name) {
        return customerJpaRepository.findByName(name);
    }

    public Customer findById(long customerId) {
        return customerJpaRepository.findById(customerId).orElseThrow();
    }

    @Override
    public Customer getBalance(long customerId) {
        return customerJpaRepository.getReferenceById(customerId);
    }
}
