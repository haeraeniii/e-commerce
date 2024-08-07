package hhplus.e_commerce.domain.order.facade;

import hhplus.e_commerce.domain.customer.entity.Customer;
import hhplus.e_commerce.domain.customer.service.CustomerService;
import hhplus.e_commerce.domain.customer.service.command.CustomerCommand;
import hhplus.e_commerce.domain.order.service.command.OrderCommand;
import hhplus.e_commerce.domain.product.entity.Product;
import hhplus.e_commerce.domain.product.service.ProductService;
import hhplus.e_commerce.domain.product.service.command.ProductCommand;
import hhplus.e_commerce.support.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Slf4j
@SpringBootTest
class OrderProductFacadeTest {

    @Autowired
    private OrderProductFacade orderProductFacade;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @BeforeEach
    void init() throws CustomException {
        Customer customer1 = customerService.registerCustomer("김종협");
        Customer customer2 = customerService.registerCustomer("허재");
        Customer customer3 = customerService.registerCustomer("정혜련");

        for (int i = 0; i < 3; i++) {
            CustomerCommand.Create customerCommand = new CustomerCommand.Create(i+1, 30000);
            customerService.charge(customerCommand);
        }
    }

    @Test
    @DisplayName("주문 동시성 테스트")
    public void orderSynchronicityTest() throws InterruptedException {
        //given
        List<ProductCommand.Create.NewProductOption> optionList = getNewProductOptions();
        ProductCommand.Create product = new ProductCommand.Create("블라우스", optionList);
        Product product1 = productService.registerProduct(product);

        Product product2 = productService.getProductDetail(product1.getId());

        List<OrderCommand.Create.NewOrderItem> orderItems = new ArrayList<>();
        OrderCommand.Create.NewOrderItem orderItem = new OrderCommand.Create.NewOrderItem(product2.getId(), product2.getProductOptionList().get(0).getId(),1);
        orderItems.add(orderItem);

        List<OrderCommand.Create> commandList = new ArrayList<>();

        OrderCommand.Create command1 = new OrderCommand.Create(
            1,
            orderItems
        );

        OrderCommand.Create command2 = new OrderCommand.Create(
                2,
                orderItems
        );

        OrderCommand.Create command3 = new OrderCommand.Create(
                3,
                orderItems
        );

        commandList.add(command1);
        commandList.add(command2);
        commandList.add(command3);

        //when
        Long startTime = System.currentTimeMillis();

        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 동시성 이슈 테스트
        IntStream.range(0, 3).forEach(i -> {
            executor.submit(() -> {
                try {
                    log.info("start for loop");
                    orderProductFacade.order(commandList.get(i));
                    log.info("주문 상품 재고 체크 = " + productService.getProductDetail(1).getProductOptionList());
                    log.info("Successful order");
                } catch (Exception e) {
                    log.info("Exception because: {}, {}", Thread.currentThread().getName(), e.toString(), e);
                } finally {
                    latch.countDown();
                }
            });

        });

        latch.await();
        executor.shutdown();

        //then
        Long endTime = System.currentTimeMillis();
        log.info("소요 시간: {}", (endTime - startTime) + "ms");
    }

    private static List<ProductCommand.Create.NewProductOption> getNewProductOptions() {
        List<ProductCommand.Create.NewProductOption> optionList = new ArrayList<>();
        ProductCommand.Create.NewProductOption option1 = new ProductCommand.Create.NewProductOption("pink", "M", 10, 10000);
        ProductCommand.Create.NewProductOption option2 = new ProductCommand.Create.NewProductOption("yellow", "M", 10, 10000);
        ProductCommand.Create.NewProductOption option3 = new ProductCommand.Create.NewProductOption("black", "M", 10, 10000);
        optionList.add(option1);
        optionList.add(option2);
        optionList.add(option3);
        return optionList;
    }
}