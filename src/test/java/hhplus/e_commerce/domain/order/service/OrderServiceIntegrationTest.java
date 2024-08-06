package hhplus.e_commerce.domain.order.service;

import hhplus.e_commerce.exception.CustomException;
import hhplus.e_commerce.domain.customer.service.CustomerService;
import hhplus.e_commerce.domain.customer.service.command.CustomerCommand;
import hhplus.e_commerce.domain.order.service.command.OrderCommand;
import hhplus.e_commerce.domain.product.entity.Product;
import hhplus.e_commerce.domain.product.entity.ProductOption;
import hhplus.e_commerce.domain.product.service.repository.ProductOptionRepository;
import hhplus.e_commerce.domain.product.service.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class OrderServiceIntegrationTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductOptionRepository productOptionRepository;

    private void dummyData () {
        Product product = new Product("팬츠");

        Product product2 = new Product("셔츠");

        Product product1 = productRepository.saveProduct(product);

        ProductOption productOption1 = ProductOption.builder()
                .product(product1)
                .color("pink")
                .size("M")
                .price(10000)
                .stock(10).build();

        productOptionRepository.save(productOption1);

        Product product2_1 = productRepository.saveProduct(product2);

        ProductOption productOption2 =  ProductOption.builder()
                .product(product2_1)
                .color("pink")
                .size("M")
                .price(10000)
                .stock(10).build();

        productOptionRepository.save(productOption2);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("주문 성공 테스트")
    public void orderTest() throws CustomException {
        //given
        dummyData();

        customerService.registerCustomer("허재");
        CustomerCommand.Create CustomerCommand = new CustomerCommand.Create(1, 300000);
        customerService.charge(CustomerCommand);

        List<OrderCommand.Create.NewOrderItem> orderItems = new ArrayList<>();
        OrderCommand.Create.NewOrderItem orderItem1 = new OrderCommand.Create.NewOrderItem(1, 1, 1);
        OrderCommand.Create.NewOrderItem orderItem2 = new OrderCommand.Create.NewOrderItem(2, 2, 1);
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);

        OrderCommand.Create command = new OrderCommand.Create(1, orderItems);

        //when
//        Order order = orderService.order(command);

        //then
//        Assertions.assertThat(order.getOrderItemList().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("주문 실패 테스트 - 재고 부족")
    public void orderFailTest_stock() throws CustomException {
        //given
        dummyData();
        customerService.registerCustomer("허재");
        CustomerCommand.Create CustomerCommand = new CustomerCommand.Create(1, 300000);
        customerService.charge(CustomerCommand);

        List<OrderCommand.Create.NewOrderItem> orderItems = new ArrayList<>();
        OrderCommand.Create.NewOrderItem orderItem1 = new OrderCommand.Create.NewOrderItem(1, 1, 1);
        OrderCommand.Create.NewOrderItem orderItem2 = new OrderCommand.Create.NewOrderItem(2, 2, 11);
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);

        OrderCommand.Create command = new OrderCommand.Create(1, orderItems);

        //when, then
//       Assertions.assertThatThrownBy(() -> orderService.order(command))
//               .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("주문 실패 테스트 - 잔액 부족")
    public void orderFailTest_balance() throws CustomException {
        //given
        dummyData();
        customerService.registerCustomer("허재");
        CustomerCommand.Create CustomerCommand = new CustomerCommand.Create(1, 10000);
        customerService.charge(CustomerCommand);

        List<OrderCommand.Create.NewOrderItem> orderItems = new ArrayList<>();
        OrderCommand.Create.NewOrderItem orderItem1 = new OrderCommand.Create.NewOrderItem(1, 1, 1);
        OrderCommand.Create.NewOrderItem orderItem2 = new OrderCommand.Create.NewOrderItem(2, 2, 1);
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);

        OrderCommand.Create command = new OrderCommand.Create(1, orderItems);

        //when, then
//        Assertions.assertThatThrownBy(() -> orderService.order(command))
//                .isInstanceOf(CustomException.class);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("동시성 테스트")
    public void orderTest_dup() throws InterruptedException, CustomException {
        //given
        dummyData();
        customerService.registerCustomer("허재");
        CustomerCommand.Create CustomerCommand = new CustomerCommand.Create(1, 300000);
        customerService.charge(CustomerCommand);

        List<OrderCommand.Create.NewOrderItem> orderItems1 = new ArrayList<>();
        OrderCommand.Create.NewOrderItem orderItem1 = new OrderCommand.Create.NewOrderItem(1, 1, 3);
        orderItems1.add(orderItem1);

//        OrderCommand.Create command1 = new OrderCommand.Create(1, orderItems1);

        customerService.registerCustomer("이석범");
        CustomerCommand.Create CustomerCommand2 = new CustomerCommand.Create(2, 300000);
        customerService.charge(CustomerCommand2);

//        List<OrderCommand.Create.NewOrderItem> orderItems2 = new ArrayList<>();
//        OrderCommand.Create.NewOrderItem orderItem2 = new OrderCommand.Create.NewOrderItem(1, 1, 3);
//        orderItems2.add(orderItem2);
//
//        OrderCommand.Create command2 = new OrderCommand.Create(2, orderItems2);

        customerService.registerCustomer("정혜련");
        CustomerCommand.Create CustomerCommand3 = new CustomerCommand.Create(3, 300000);
        customerService.charge(CustomerCommand3);

//        List<OrderCommand.Create.NewOrderItem> orderItems3 = new ArrayList<>();
//        OrderCommand.Create.NewOrderItem orderItem3 = new OrderCommand.Create.NewOrderItem(1, 1, 4);
//        orderItems2.add(orderItem3);

//        OrderCommand.Create command3 = new OrderCommand.Create(3, orderItems3);

//        List<OrderCommand.Create> commandList = new ArrayList<>();
//        commandList.add(command1);
//        commandList.add(command2);
//        commandList.add(command3);

        int threadCount = 3;

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        CountDownLatch latch = new CountDownLatch (threadCount);

        //when
//        for (int i = 0; i < threadCount; i++) {
//            int finalI = i;
//            executorService.execute(() -> {
//
//                try {
//                    orderService.order(new OrderCommand.Create(finalI+1, orderItems1));
//
//                } catch (CustomException e) {
//                    throw new RuntimeException(e);
//                } finally {
//                    latch.countDown();
//                }
//            });
//        }
//
//        latch.await();

        //when, then
        System.out.println(productOptionRepository.getById(1));
    }
}