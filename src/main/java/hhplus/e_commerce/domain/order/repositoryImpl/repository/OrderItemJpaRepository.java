package hhplus.e_commerce.domain.order.repositoryImpl.repository;

import hhplus.e_commerce.domain.order.entity.OrderItem;
import hhplus.e_commerce.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

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
