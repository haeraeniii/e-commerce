package hhplus.e_commerce.domain.product.controller;

import hhplus.e_commerce.domain.product.entity.Product;
import hhplus.e_commerce.domain.product.service.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    @Transactional
    @Rollback(false)
    public void save() throws Exception {
        //given
        Product product = new Product();
        product.setId(0);
        product.setTitle("블라우스");
        product.setPrice(30000);

        productRepository.saveProduct(product);

        //when

        //then
    }
}