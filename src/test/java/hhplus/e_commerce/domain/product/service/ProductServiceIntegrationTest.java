package hhplus.e_commerce.domain.product.service;

import hhplus.e_commerce.domain.order.controller.dto.mapper.OrderMapper;
import hhplus.e_commerce.domain.product.entity.Product;
import hhplus.e_commerce.domain.product.service.command.ProductCommand;
import hhplus.e_commerce.domain.product.service.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductServiceIntegrationTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    OrderMapper orderMapper;

    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("인기상품 top5 쿼리 조회 테스트")
    public void topProductTest() {
        ProductCommand.Create.NewProductOption opts = new ProductCommand.Create.NewProductOption(
                "pink", "M", 10, 10000
        );

        List<ProductCommand.Create.NewProductOption> options = new ArrayList<>();
        options.add(opts);

        ProductCommand.Create product1 = new ProductCommand.Create("팬츠", options);
        ProductCommand.Create product2 = new ProductCommand.Create("셔츠", options);
        ProductCommand.Create product3 = new ProductCommand.Create("벨트", options);
        ProductCommand.Create product4 = new ProductCommand.Create("코트", options);
        ProductCommand.Create product5 = new ProductCommand.Create("부츠", options);

        productService.registerProduct(product1);
        productService.registerProduct(product2);
        productService.registerProduct(product3);
        productService.registerProduct(product4);
        productService.registerProduct(product5);

        System.out.println(productRepository.findTopProduct(LocalDateTime.now().minusDays(3), LocalDateTime.now()));
    }

    @Test
    @Transactional
    @Rollback(false)
    @DisplayName("cachedProductTest")
    public void cachedProductTest() throws Exception {
        //given
        List<ProductCommand.Create.NewProductOption> newProductOptionList = new ArrayList<>();
        ProductCommand.Create.NewProductOption newProductOption = new ProductCommand.Create.NewProductOption("pink", "M", 10, 30000);
        newProductOptionList.add(newProductOption);
        ProductCommand.Create command = new ProductCommand.Create("블라우스", newProductOptionList);

        Product product = productService.registerProduct(command);

        //when
        Long productId = product.getId();
        Product found = productService.getProductDetail(productId);

        //then
        Assertions.assertEquals(productId, found.getId());
    }
}
