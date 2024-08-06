package hhplus.e_commerce.domain.customer.repository;

import hhplus.e_commerce.domain.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {
    Customer findByName(String name);
}
