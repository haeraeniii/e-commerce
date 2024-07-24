package hhplus.e_commerce.domain.order.repository.repositoryImpl;

import hhplus.e_commerce.domain.order.entity.OrderSheet;
import hhplus.e_commerce.domain.order.repository.OrderSheetJpaRepository;
import hhplus.e_commerce.domain.order.service.repository.OrderSheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderSheetRepositoryImpl implements OrderSheetRepository {
    private final OrderSheetJpaRepository orderSheetJpaRepository;
    @Override
    public OrderSheet save(OrderSheet orderSheet) {
        return orderSheetJpaRepository.save(orderSheet);
    }

    @Override
    public OrderSheet getOrderSheet(long customerId) {
        return orderSheetJpaRepository.findByCustomerId(customerId);
    }
}
