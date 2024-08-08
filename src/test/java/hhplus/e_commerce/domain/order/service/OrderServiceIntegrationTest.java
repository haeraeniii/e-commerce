package hhplus.e_commerce.domain.order.service;

import hhplus.e_commerce.domain.order.entity.Order;
import hhplus.e_commerce.support.exception.CustomException;
import hhplus.e_commerce.domain.customer.service.CustomerService;
import hhplus.e_commerce.domain.customer.service.command.CustomerCommand;
import hhplus.e_commerce.domain.order.service.command.OrderCommand;
import hhplus.e_commerce.domain.product.service.repository.ProductOptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@SpringBootTest
class OrderServiceIntegrationTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductOptionRepository productOptionRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("주문 성공 테스트")
    public void orderTest() throws CustomException {
        //given
        customerService.registerCustomer("정혜련");
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
        List<OrderCommand.Create.NewOrderItem> orderItems1 = new ArrayList<>();
        OrderCommand.Create.NewOrderItem orderItem1 = new OrderCommand.Create.NewOrderItem(1, 1, 3);
        orderItems1.add(orderItem1);

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

    @Test
    @Transactional
    @Rollback(false)
    @DisplayName("주문내역 전체 리스트")
    public void getOrderListIndexTest() {
        //given
        Long startTime = System.currentTimeMillis();
        List<Order> orderList = orderService.getOrderList();

        //when, then
        log.info("orderListResult : " + orderList);
        Long endTime = System.currentTimeMillis();
        log.info("소요 시간: {}", (endTime - startTime) + "ms");
    }

    @Test
    @Transactional
    @DisplayName("주문내역 특정 고객 리스트 - 인덱스 비교")
    public void getMyOrderListTest() {
        //given
        Long startTime = System.currentTimeMillis();
        List<Order> myOrderList = orderService.getMyOrderList(1);

        //when, then
        log.info(myOrderList.toString());
        Long endTime = System.currentTimeMillis();
        log.info("소요 시간: {}", (endTime - startTime) + "ms");
    }

    @Test
    @Transactional
    @DisplayName("주문내역 특정 날짜 리스트 - 인덱스 비교")
    public void getOrderListWithDateTest() {
        //given
        Long startTime = System.currentTimeMillis();
        List<Order> orderListWithDate = orderService.getOrderListWithDate(LocalDateTime.now().minusDays(3), LocalDateTime.now());

        //when, then
        log.info(orderListWithDate.toString());
        Long endTime = System.currentTimeMillis();
        log.info("소요 시간: {}", (endTime - startTime) + "ms");
    }

    @Test
    @Transactional
    @DisplayName("주문내역 특정 고객과 특정 날짜 리스트 - 복합 인덱스 비교")
    public void getOrderListWithWhoAndDateTest() {
        //given
        Long startTime = System.currentTimeMillis();
        List<Order> orderListWithWhoAndDate = orderService.getOrderListWithWhoAndDate(1, LocalDateTime.now().minusDays(3), LocalDateTime.now());

        //when, then
        log.info(orderListWithWhoAndDate.toString());
        Long endTime = System.currentTimeMillis();
        log.info("소요 시간: {}", (endTime - startTime) + "ms");
    }
}