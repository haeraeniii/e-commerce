package hhplus.e_commerce.domain.order.repositoryImpl;

import hhplus.e_commerce.domain.order.entity.OrderItem;
import hhplus.e_commerce.domain.order.repositoryImpl.repository.OrderItemJpaRepository;
import hhplus.e_commerce.domain.order.service.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderItemRepositoryImpl implements OrderItemRepository {
    private final OrderItemJpaRepository orderItemJpaRepository;

    @Override
    public List<OrderItem> saveAll(List<OrderItem> orderItems) {
        return orderItemJpaRepository.saveAll(orderItems);
    }
}
