package hhplus.e_commerce.domain.order.repository;

import hhplus.e_commerce.domain.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJpaRepository extends JpaRepository<OrderItem, Long> {
//    @Query(value = "SELECT oi.productId " +
//            "FROM OrderItem oi " +
//            "JOIN oi.order o " +
//            "WHERE o.createdAt BETWEEN :startDate AND :endDate " +
//            "GROUP BY oi.productId " +
//            "ORDER BY SUM(oi.orderQuantity) DESC", nativeQuery = true)
//    List<Product> findTopProduct(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


//    @Query(value = "SELECT * FROM order_item", nativeQuery = true)
//    List<OrderItem> findTopProduct(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
