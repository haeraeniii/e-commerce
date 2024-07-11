package hhplus.e_commerce.domain.customer.service;

import hhplus.e_commerce.domain.customer.entity.Customer;
import hhplus.e_commerce.domain.customer.service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    // 잔액 조회
    public Customer checkBalance(long customerId) {
        return customerRepository.getBalance(customerId);
    }

    // 금액 충전
    public void charge(Customer customer) {
        Customer customer1 = customerRepository.getCustomer(customer.getId());

        customer1.chargeAmount(customer.getBalance());

        customerRepository.save(customer1);
    }
}
