package hhplus.e_commerce.domain.order.service.repository;

import hhplus.e_commerce.domain.order.entity.OrderItemSheet;

import java.util.List;

public interface OrderItemSheetRepository {
    List<OrderItemSheet> saveAll(List<OrderItemSheet> orderItemSheetList);
}
