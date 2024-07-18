package hhplus.e_commerce.domain.product.service.repository;

import hhplus.e_commerce.domain.product.entity.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository {
    Product saveProduct (Product product);

    List<Product> saveAllProduct (List<Product> products);

    List<Product> getProductList ();

    Product getProduct (long id);

    List<Product> findAllById(List<Long> productIds);

    List<Product> findTopProduct(LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Product> findAll ();
}
