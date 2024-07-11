package hhplus.e_commerce.domain.order.repositoryImpl;

import hhplus.e_commerce.domain.order.entity.Order;
import hhplus.e_commerce.domain.order.repositoryImpl.repository.OrderJpaRepository;
import hhplus.e_commerce.domain.order.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order save(Order order) {
        return null;
    }

    @Override
    public List<Order> saveAll(List<Order> orders) {
        return orderJpaRepository.saveAll(orders);
    }

    @Override
    public List<Order> getOrderList() {
        return orderJpaRepository.findAll();
    }

    @Override
    public List<Order> getOrder3days() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime before3days = now.minusDays(4);

        return orderJpaRepository.findAllByCreateAtIsAfter(before3days);
    }
}
