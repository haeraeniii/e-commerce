package hhplus.e_commerce.domain.product.service;

import hhplus.e_commerce.domain.customer.service.CustomerService;
import hhplus.e_commerce.domain.order.controller.dto.mapper.OrderMapper;
import hhplus.e_commerce.domain.product.entity.Product;
import hhplus.e_commerce.domain.product.service.command.ProductCommand;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
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
    OrderMapper orderMapper;

    @Test
    @Transactional
    @DisplayName("인기상품 top5 쿼리 조회 테스트")
    public void topProductTest() {
        Long startTime = System.currentTimeMillis();
        List<Product> topProduct = productService.findTopProduct(LocalDateTime.now().minusDays(4), LocalDateTime.now().minusDays(1));

        //when, then
        log.info(topProduct.toString());
        Long endTime = System.currentTimeMillis();
        log.info("소요 시간: {}", (endTime - startTime) + "ms");
    }

    @Test
    @Transactional
    @Rollback(false)
    @DisplayName("cachedProductTest")
    public void cachedProductTest() {
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
