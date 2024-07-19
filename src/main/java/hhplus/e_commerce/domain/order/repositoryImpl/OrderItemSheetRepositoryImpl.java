package hhplus.e_commerce.domain.order.repositoryImpl;

import hhplus.e_commerce.domain.order.entity.OrderItemSheet;
import hhplus.e_commerce.domain.order.repositoryImpl.repository.OrderItemSheetJpaRepository;
import hhplus.e_commerce.domain.order.service.repository.OrderItemSheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderItemSheetRepositoryImpl implements OrderItemSheetRepository {

    private final OrderItemSheetJpaRepository orderItemSheetJpaRepository;
    @Override
    public List<OrderItemSheet> saveAll(List<OrderItemSheet> orderItemSheetList) {
        return orderItemSheetJpaRepository.saveAll(orderItemSheetList);
    }
}
