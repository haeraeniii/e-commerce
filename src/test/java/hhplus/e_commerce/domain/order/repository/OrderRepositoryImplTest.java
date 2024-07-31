package hhplus.e_commerce.domain.order.repository;

import hhplus.e_commerce.domain.order.entity.Order;
import hhplus.e_commerce.domain.order.entity.OrderItem;
import hhplus.e_commerce.domain.order.repository.repositoryImpl.OrderItemRepositoryImpl;
import hhplus.e_commerce.domain.order.repository.repositoryImpl.OrderRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class OrderRepositoryImplTest {

    @Autowired
    private OrderRepositoryImpl orderRepositoryImpl;

    @Autowired
    private OrderItemRepositoryImpl orderItemRepositoryImpl;

    @Test
    @Transactional
    @Rollback(value = false)
    public void top5Test() {
        //given
        OrderItem orderItem1 = OrderItem.builder().productOptionId(1).productName("블라우스").color("pink").size("M").price(30000).build();

        OrderItem orderItem2 = OrderItem.builder().productOptionId(2).productName("셔츠").color("yellow").size("L").price(35000).build();

        OrderItem orderItem3 = OrderItem.builder().productOptionId(3).productName("팬츠").color("black").size("S").price(40000).build();

        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(orderItem1);
        orderItemList.add(orderItem2);
        orderItemList.add(orderItem3);

        OrderItem orderItem4 = OrderItem.builder().productOptionId(1).productName("블라우스").color("pink").size("M").price(30000).build();

        OrderItem orderItem5 = OrderItem.builder().productOptionId(2).productName("셔츠").color("yellow").size("L").price(35000).build();

        OrderItem orderItem6 = OrderItem.builder().productOptionId(3).productName("팬츠").color("black").size("S").price(40000).build();

        List<OrderItem> orderItemList2 = new ArrayList<>();
        orderItemList2.add(orderItem4);
        orderItemList2.add(orderItem5);
        orderItemList2.add(orderItem6);

        List<OrderItem> orderItems = orderItemRepositoryImpl.saveAll(orderItemList);
        List<OrderItem> orderItems2 = orderItemRepositoryImpl.saveAll(orderItemList2);



        Order order1 = new Order(1);

        Order order2 = new Order(1);

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);

        orderRepositoryImpl.saveAll(orders);

        //when

        //then

    }
}