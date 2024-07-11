package hhplus.e_commerce.domain.customer.repositoryImpl;

import hhplus.e_commerce.domain.customer.entity.Customer;
import hhplus.e_commerce.domain.customer.repositoryImpl.repository.CustomerJpaRepository;
import hhplus.e_commerce.domain.customer.service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
    public Customer getBalance(long customerId) {
        return customerJpaRepository.getReferenceById(customerId);
    }
}
