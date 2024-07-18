package hhplus.e_commerce.domain.order.service;

import hhplus.e_commerce.base.exception.CustomException;
import hhplus.e_commerce.domain.customer.service.CustomerService;
import hhplus.e_commerce.domain.customer.service.dto.CustomerCommand;
import hhplus.e_commerce.domain.order.entity.Order;
import hhplus.e_commerce.domain.order.service.dto.OrderCommand;
import hhplus.e_commerce.domain.product.entity.Product;
import hhplus.e_commerce.domain.product.entity.ProductOption;
import hhplus.e_commerce.domain.product.service.repository.ProductOptionRepository;
import hhplus.e_commerce.domain.product.service.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceTest2 {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductOptionRepository productOptionRepository;

    private void dummyData () {
        Product product = new Product();
        product.setTitle("팬츠");
        product.setPrice(40000);

        Product product2 = new Product();
        product2.setTitle("셔츠");
        product2.setPrice(40000);

        Product product1 = productRepository.saveProduct(product);

        ProductOption productOption1 = new ProductOption();
        productOption1.setProduct(product1);
        productOption1.setColor("pink");
        productOption1.setStock(10);
        productOption1.setSize("M");

        productOptionRepository.save(productOption1);

        Product product2_1 = productRepository.saveProduct(product2);

        ProductOption productOption2 = new ProductOption();
        productOption2.setProduct(product2_1);
        productOption2.setColor("pink");
        productOption2.setStock(10);
        productOption2.setSize("M");

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
        Order order = orderService.order(command);

        //then
        Assertions.assertThat(order.getOrderItemList().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("주문 실패 테스트 - 재고 부족")
    public void orderFailTest_stock() {
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
       Assertions.assertThatThrownBy(() -> orderService.order(command))
               .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("주문 실패 테스트 - 잔액 부족")
    public void orderFailTest_balance(){
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
        Assertions.assertThatThrownBy(() -> orderService.order(command))
                .isInstanceOf(CustomException.class);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("동시성 테스트")
    public void orderTest_dup(){
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
        Assertions.assertThatThrownBy(() -> orderService.order(command))
                .isInstanceOf(CustomException.class);
    }
}