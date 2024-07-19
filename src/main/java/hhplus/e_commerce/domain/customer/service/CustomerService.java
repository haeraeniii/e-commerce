package hhplus.e_commerce.domain.customer.service;

import hhplus.e_commerce.base.exception.CustomException;
import hhplus.e_commerce.domain.customer.entity.Customer;
import hhplus.e_commerce.domain.customer.service.dto.CustomerCommand;
import hhplus.e_commerce.domain.customer.service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    //고객 등록
    @Transactional
    public Customer registerCustomer (String name) throws CustomException {
        Customer findCustomer = customerRepository.findByName(name);

        if(findCustomer != null) {
           throw new CustomException(CustomException.ExceptionType.HAS_SAME_USER);
        }

        Customer customer = new Customer();
        customer.setName(name);

        return customerRepository.save(customer);
    }

    // 잔액 조회
    public Customer checkBalance(long customerId) {
        return customerRepository.getBalance(customerId);
    }

    // 금액
    @Transactional
    public Customer charge(CustomerCommand.Create command) {
        Customer customer = customerRepository.getCustomer(command.customerId());

        customer.setBalance(customer.getBalance() + command.balance());

        return customer;
    }
}
