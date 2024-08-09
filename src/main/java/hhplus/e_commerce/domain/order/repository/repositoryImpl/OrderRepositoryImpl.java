package hhplus.e_commerce.domain.order.repository.repositoryImpl;

import hhplus.e_commerce.domain.order.entity.Order;
import hhplus.e_commerce.domain.order.repository.OrderJpaRepository;
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
        return orderJpaRepository.save(order);
    }

    @Override
    public List<Order> saveAll(List<Order> orders) {
        return orderJpaRepository.saveAll(orders);
    }

    @Override
    public Order getOrder(long orderId) {
        return orderJpaRepository.findById(orderId).orElse(null);
    }

    @Override
    public List<Order> getOrderList() {
        return orderJpaRepository.findAll();
    }

    @Override
    public List<Order> getMyOrderList(long customerId) {
        return orderJpaRepository.findAllByCustomerId(customerId);
    }

    @Override
    public List<Order> getOrderListWithDate(LocalDateTime startDate, LocalDateTime endDate) {
        return orderJpaRepository.findAllByStartDateAndEndDate(startDate, endDate);
    }

    @Override
    public List<Order> getOrderListWithWhoAndDate(long customerId, LocalDateTime startDate, LocalDateTime endDate) {
        return orderJpaRepository.findAllByCustomerIdAndStartDateAndEndDate(customerId, startDate, endDate);
    }
}
