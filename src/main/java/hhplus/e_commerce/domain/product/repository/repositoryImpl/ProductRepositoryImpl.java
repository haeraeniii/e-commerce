package hhplus.e_commerce.domain.product.repository.repositoryImpl;

import hhplus.e_commerce.domain.product.entity.Product;
import hhplus.e_commerce.domain.product.repository.ProductJpaRepository;
import hhplus.e_commerce.domain.product.service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductJpaRepository productJpaRepository;

    @Override
    public Product saveProduct(Product product) {
        return productJpaRepository.save(product);
    }

    @Override
    public List<Product> saveAllProduct(List<Product> products) {
        return productJpaRepository.saveAll(products);
    }

    @Override
    public List<Product> getProductList() {
        return productJpaRepository.findAll();
    }

    @Override
    public Product getProduct(long id) {
        return productJpaRepository.getReferenceById(id);
    }

    @Override
    public List<Product> findAllById(List<Long> productIds) {
        return productJpaRepository.findAllById(productIds);
    }

    @Override
    public List<Product> findTopProduct(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return productJpaRepository.findTopProduct(startDateTime, endDateTime);
    }

    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll();
    }
}
