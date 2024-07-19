package hhplus.e_commerce.domain.order.repositoryImpl.repository;

import hhplus.e_commerce.domain.order.entity.OrderItemSheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemSheetJpaRepository extends JpaRepository<OrderItemSheet, Long> {
}
