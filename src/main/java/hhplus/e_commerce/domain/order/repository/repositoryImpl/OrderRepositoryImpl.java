package hhplus.e_commerce.domain.order.repository.repositoryImpl;

import hhplus.e_commerce.domain.order.entity.Order;
import hhplus.e_commerce.domain.order.repository.OrderJpaRepository;
import hhplus.e_commerce.domain.order.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order save(Order order) {
        return orderJpaRepository.save(order);
    }

    @Override
    public List<Order> saveAll(List<Order> orders) {
        return orderJpaRepository.saveAll(orders);
    }

    @Override
    public Order getOrder(long id) {
        return orderJpaRepository.getReferenceById(id);
    }

    @Override
    public List<Order> getOrderList() {
        return orderJpaRepository.findAll();
    }
}
