package hhplus.e_commerce.domain.product.repositoryImpl;

import hhplus.e_commerce.domain.product.entity.ProductOption;
import hhplus.e_commerce.domain.product.repositoryImpl.repository.ProductOptionJpaRepository;
import hhplus.e_commerce.domain.product.service.repository.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductOptionRepositoryImpl implements ProductOptionRepository {
    private final ProductOptionJpaRepository productOptionJpaRepository;

    @Override
    public ProductOption getProductOption(long id) {
        return productOptionJpaRepository.getReferenceById(id);
    }

    @Override
    public ProductOption save(ProductOption productOption) {
        return productOptionJpaRepository.save(productOption);
    }

    @Override
    public List<ProductOption> saveAll(List<ProductOption> productOptionList) {
        return productOptionJpaRepository.saveAllAndFlush(productOptionList);
    }

    @Override
    public List<ProductOption> findAllById(List<Long> productOptionIds) {
        return productOptionJpaRepository.findAllById(productOptionIds);
    }
}
