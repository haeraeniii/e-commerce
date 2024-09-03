package hhplus.e_commerce.domain.order.service.repository;

import hhplus.e_commerce.domain.order.entity.OrderItem;

import java.util.List;

public interface OrderItemRepository {

    List<OrderItem> saveAll(List<OrderItem> orderItems);
}
