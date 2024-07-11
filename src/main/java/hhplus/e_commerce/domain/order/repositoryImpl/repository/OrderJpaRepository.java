package hhplus.e_commerce.domain.order.repositoryImpl.repository;

import hhplus.e_commerce.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCreateAtIsAfter(LocalDateTime before3days);
}
