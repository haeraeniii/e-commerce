package hhplus.e_commerce.domain.customer.service.repository;

import hhplus.e_commerce.domain.customer.entity.Customer;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.Optional;

public interface CustomerRepository {

    Customer getCustomer (long customerId);

    Customer findByName (String name);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "10000")})
    Customer findById(long customerId);
    Customer getBalance(long customerId);

    Customer save(Customer customer);
}
