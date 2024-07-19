package hhplus.e_commerce.domain.product.repositoryImpl.repository;

import hhplus.e_commerce.domain.product.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOptionJpaRepository extends JpaRepository<ProductOption, Long> {
}
