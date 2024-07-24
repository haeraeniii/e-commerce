package hhplus.e_commerce.domain.order.service.repository;

import hhplus.e_commerce.domain.order.entity.Order;

import java.util.List;

public interface OrderRepository {

    Order save(Order order);

    List<Order> saveAll(List<Order> order);

    Order getOrder(long id);

    List<Order> getOrderList();
}
