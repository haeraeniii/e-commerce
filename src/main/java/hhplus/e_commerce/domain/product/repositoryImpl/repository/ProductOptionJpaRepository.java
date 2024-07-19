package hhplus.e_commerce.domain.product.repositoryImpl.repository;

import hhplus.e_commerce.domain.product.entity.ProductOption;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.List;

public interface ProductOptionJpaRepository extends JpaRepository<ProductOption, Long> {
}
