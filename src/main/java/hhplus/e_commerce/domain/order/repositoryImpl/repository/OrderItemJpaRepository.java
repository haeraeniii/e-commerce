package hhplus.e_commerce.domain.order.repositoryImpl.repository;

import hhplus.e_commerce.domain.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJpaRepository extends JpaRepository<OrderItem, Long> {
}
