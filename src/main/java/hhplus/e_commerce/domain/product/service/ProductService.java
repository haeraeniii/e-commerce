package hhplus.e_commerce.domain.product.service;

import hhplus.e_commerce.domain.product.entity.Product;
import hhplus.e_commerce.domain.product.entity.ProductOption;
import hhplus.e_commerce.domain.product.service.repository.ProductOptionRepository;
import hhplus.e_commerce.domain.product.service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;

    //상품 등록
    public void registerProduct (Product product) {
        Product product1 = productRepository.saveProduct(product);
        List<ProductOption> productOptionList = product.getProductOptionList().stream().map(option -> {
            ProductOption option1 = new ProductOption();
            option1.setProduct(product1);
            option1.setColor(option.getColor());
            option1.setSize(option.getSize());
            option1.setStock(option.getStock());

            return option1;
        }).toList();

        productOptionRepository.saveAll(productOptionList);
    }

    // 상품 목록 조회
    public List<Product> getProductList () {
        return productRepository.getProductList();
    }

    // 상품 상세 조회
    public Product getProductDetail (long id) {
        return productRepository.getProduct(id);
    }

    /**
     * 최근 3일 주문 내역 중 Top5 불러오기
     */
    public List<Product> findTopProduct(LocalDateTime startDate, LocalDateTime endDate) {
        return productRepository.findTopProduct(startDate, endDate);
    }
}
