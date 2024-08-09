package hhplus.e_commerce.domain.order.repository;

import hhplus.e_commerce.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomerId(long customerId);

    @Query(value = "SELECT o FROM Order AS o WHERE o.createdAt BETWEEN :startDate AND :endDate")
    List<Order> findAllByStartDateAndEndDate(LocalDateTime startDate, LocalDateTime endDate);

    @Query(value = "SELECT o FROM Order AS o WHERE o.customerId = :customerId AND o.createdAt BETWEEN :startDate AND :endDate")
    List<Order> findAllByCustomerIdAndStartDateAndEndDate(long customerId, LocalDateTime startDate, LocalDateTime endDate);
}
