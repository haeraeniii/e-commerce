package hhplus.e_commerce.domain.customer.repositoryImpl;

import hhplus.e_commerce.domain.customer.repositoryImpl.repository.OrderSheetJpaRepository;
import hhplus.e_commerce.domain.customer.service.repository.OrderSheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderSheetRepositoryImpl implements OrderSheetRepository {
    private final OrderSheetJpaRepository orderFormJpaRepository;
}
