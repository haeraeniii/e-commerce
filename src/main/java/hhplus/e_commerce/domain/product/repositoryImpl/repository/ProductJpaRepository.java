package hhplus.e_commerce.domain.product.repositoryImpl.repository;

import hhplus.e_commerce.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {
}
