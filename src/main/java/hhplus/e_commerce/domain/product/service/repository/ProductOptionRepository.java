package hhplus.e_commerce.domain.product.service.repository;

import hhplus.e_commerce.domain.product.entity.ProductOption;
import jakarta.persistence.LockModeType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductOptionRepository {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM ProductOption p WHERE p.id = :id")
    @Cacheable(cacheNames = "productDetail", key = "#id")
    ProductOption getById(long id);

    ProductOption save(ProductOption productOption);

    List<ProductOption> saveAll(List<ProductOption> productOptionList);

    List<ProductOption> findAllById(List<Long> productOptionIds);
}
