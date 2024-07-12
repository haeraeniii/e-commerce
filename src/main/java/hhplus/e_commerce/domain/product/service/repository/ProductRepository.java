package hhplus.e_commerce.domain.product.service.repository;

import hhplus.e_commerce.domain.product.entity.Product;

import java.util.List;

public interface ProductRepository {
    Product saveProduct (Product product);

    List<Product> getProductList ();

    Product getProduct (long id);

    List<Product> findAllById(List<Long> productIds);
}
