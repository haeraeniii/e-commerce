package hhplus.e_commerce.domain.order.repository;

import hhplus.e_commerce.domain.order.entity.OrderSheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderSheetJpaRepository extends JpaRepository<OrderSheet, Long> {
    OrderSheet findByCustomerId(long customerId);
}
