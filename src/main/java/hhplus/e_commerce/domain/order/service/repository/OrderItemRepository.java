package hhplus.e_commerce.domain.order.service.repository;

import hhplus.e_commerce.domain.order.entity.OrderItem;
import hhplus.e_commerce.domain.product.entity.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderItemRepository {

    List<OrderItem> saveAll(List<OrderItem> orderItems);

//    List<Product> findTopProduct(LocalDateTime startDate, LocalDateTime endDate);
}
