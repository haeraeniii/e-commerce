package hhplus.e_commerce.domain.product.repositoryImpl;

import hhplus.e_commerce.domain.product.entity.Product;
import hhplus.e_commerce.domain.product.repositoryImpl.repository.ProductJpaRepository;
import hhplus.e_commerce.domain.product.service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
    public List<Product> getProductList() {
        return productJpaRepository.findAll();
    }

    @Override
    public Product getProduct(long id) {
        return productJpaRepository.getReferenceById(id);
    }
}
