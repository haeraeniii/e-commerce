package hhplus.e_commerce.domain.product.service.repository;

import hhplus.e_commerce.domain.product.entity.ProductOption;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.List;

public interface ProductOptionRepository {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "10000")})
    ProductOption getById(long id);

    ProductOption save(ProductOption productOption);

    List<ProductOption> saveAll(List<ProductOption> productOptionList);

    List<ProductOption> findAllById(List<Long> productOptionIds);
}
