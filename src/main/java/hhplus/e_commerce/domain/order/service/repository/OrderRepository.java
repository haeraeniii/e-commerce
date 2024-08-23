package hhplus.e_commerce.domain.order.service.repository;

import hhplus.e_commerce.domain.order.entity.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository {

    Order save(Order order);

    List<Order> saveAll(List<Order> order);

    Order getOrder(long orderId);

    List<Order> getOrderList();

    List<Order> getMyOrderList(long customerId);

    List<Order> getOrderListWithDate(LocalDateTime startDate, LocalDateTime endDate);

    List<Order> getOrderListWithWhoAndDate(long customerId, LocalDateTime startDate, LocalDateTime endDate);
}
