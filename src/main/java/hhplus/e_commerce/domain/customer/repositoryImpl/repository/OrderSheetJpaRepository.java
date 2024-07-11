package hhplus.e_commerce.domain.customer.repositoryImpl.repository;

import hhplus.e_commerce.domain.customer.entity.OrderSheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderSheetJpaRepository extends JpaRepository<OrderSheet, Long> {
}
