package hhplus.e_commerce.domain.product.service;

import hhplus.e_commerce.domain.order.service.command.OrderCommand;
import hhplus.e_commerce.domain.product.entity.Product;
import hhplus.e_commerce.domain.product.entity.ProductOption;
import hhplus.e_commerce.domain.product.service.command.OrderProductCommand;
import hhplus.e_commerce.domain.product.service.command.ProductCommand;
import hhplus.e_commerce.domain.product.service.dto.OrderOptionAndQuantityDto;
import hhplus.e_commerce.domain.product.service.dto.OrderProductDto;
import hhplus.e_commerce.domain.product.service.repository.ProductOptionRepository;
import hhplus.e_commerce.domain.product.service.repository.ProductRepository;
import hhplus.e_commerce.support.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;



    //상품 등록
    @Transactional
//    @CachePut(cacheNames = "product", key = "#result.id")
    public Product registerProduct (ProductCommand.Create command) {
        Product product = new Product(command.title());

        Product product1 = productRepository.saveProduct(product);
        List<ProductOption> productOptionList = command.newProductOptionList().stream().map(option -> {
            ProductOption option1 = ProductOption.builder()
                    .product(product1)
                    .color(option.color())
                    .size(option.size())
                    .stock(option.stock())
                    .price(option.price()).build();

            return option1;
        }).toList();

        productOptionRepository.saveAll(productOptionList);

        return product1;
    }

    // 상품 목록 조회
    public List<Product> getProductList () {
        return productRepository.getProductList();
    }

    // 상품 상세 조회
    @Transactional(readOnly = true)
//    @Cacheable(cacheNames = "productDetail", key = "#id")
    public Product getProductDetail (long id) {
        Product product = productRepository.getProduct(id);

        Hibernate.initialize(product.getProductOptionList());
        return product;
    }

    /**
     * 최근 3일 주문 내역 중 Top5 불러오기
     */
//    @Cacheable(cacheNames = "topProduct", key = "#id")
    public List<Product> findTopProduct(LocalDateTime startDate, LocalDateTime endDate) {
        return productRepository.findTopProduct(startDate, endDate);
    }

    /**
     * 재고 차감, 전체 주문 금액 구하기
     */
    @Transactional
    public OrderProductCommand.Create deductStockAndTotalAmount(List<OrderCommand.Create.NewOrderItem> newOrderItemList) {
        List<ProductOption> productOptionList = new ArrayList<>();
        List<OrderOptionAndQuantityDto> orderOptionAndQuantityDtos = new ArrayList<>();

        long totalPrice = newOrderItemList
            .stream().map(it -> {
                log.info("productOption 조회 lock PESSIMISTIC_WRITE");
                ProductOption option = productOptionRepository.getById(it.productOptionId());
                log.info("productOption : {}", option.getStock());
                try {
                    option.deductStock(it.quantity());
                    productOptionList.add(option);
                    orderOptionAndQuantityDtos.add(new OrderOptionAndQuantityDto(option, it.quantity()));

                } catch (CustomException e) {
                    throw new RuntimeException(e);
                }

                return it.quantity() * option.getPrice();
            }).mapToLong(i -> i).sum();

        productOptionRepository.saveAll(productOptionList);

        OrderProductDto orderProductDto = new OrderProductDto(totalPrice, orderOptionAndQuantityDtos);

        return orderProductDto.toCommand();
    }
}
