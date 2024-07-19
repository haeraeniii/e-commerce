package hhplus.e_commerce.domain.product.service;

import hhplus.e_commerce.domain.order.controller.dto.mapper.OrderMapper;
import hhplus.e_commerce.domain.order.service.OrderService;
import hhplus.e_commerce.domain.product.entity.Product;
import hhplus.e_commerce.domain.product.entity.ProductOption;
import hhplus.e_commerce.domain.product.service.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootTest
public class ProductServiceTest2 {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    OrderMapper orderMapper;

    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("인기상품 top5 쿼리 조회 테스트")
    public void topProductTest() {
        Product product = new Product();
        product.setTitle("팬츠");
        product.setPrice(40000);

        Product product2 = new Product();
        product2.setTitle("셔츠");
        product2.setPrice(40000);

        Product product3 = new Product();
        product3.setTitle("신발");
        product3.setPrice(40000);

        Product product4 = new Product();
        product4.setTitle("모자");
        product4.setPrice(40000);

        Product product5 = new Product();
        product5.setTitle("부츠");
        product5.setPrice(40000);

        ProductOption productOption = new ProductOption();
        productOption.setColor("pink");
        productOption.setStock(1111);
        productOption.setSize("M");

        ArrayList<ProductOption> opts = new ArrayList<>();
        opts.add(productOption);


        product.setProductOptionList(opts);
        product2.setProductOptionList(opts);
        product3.setProductOptionList(opts);
        product4.setProductOptionList(opts);
        product5.setProductOptionList(opts);

        productService.registerProduct(product);
        productService.registerProduct(product2);
        productService.registerProduct(product3);
        productService.registerProduct(product4);
        productService.registerProduct(product5);

        System.out.println(productRepository.findTopProduct(LocalDateTime.now().minusDays(3), LocalDateTime.now()));
    }
}
