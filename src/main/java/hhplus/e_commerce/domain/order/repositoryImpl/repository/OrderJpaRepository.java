package hhplus.e_commerce.domain.order.repositoryImpl.repository;

import hhplus.e_commerce.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
}
