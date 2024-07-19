package hhplus.e_commerce.domain.product.repositoryImpl.repository;

import hhplus.e_commerce.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p FROM Product AS p " +
            "JOIN OrderItem AS oi ON p.id = oi.productId " +
            "JOIN Order o ON o.id = oi.order.id " +
            "WHERE o.createdAt BETWEEN :startDateTime AND :endDateTime " +
            "GROUP BY oi.productId " +
            "ORDER BY SUM(oi.orderQuantity) DESC")
    List<Product> findTopProduct(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
