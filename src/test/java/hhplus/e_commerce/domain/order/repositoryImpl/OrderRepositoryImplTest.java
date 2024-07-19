package hhplus.e_commerce.domain.order.repositoryImpl;

import hhplus.e_commerce.domain.order.entity.Order;
import hhplus.e_commerce.domain.order.entity.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderRepositoryImplTest {

    @Autowired
    private OrderRepositoryImpl orderRepositoryImpl;

    @Autowired
    private OrderItemRepositoryImpl orderItemRepositoryImpl;

    private void orderItem(OrderItem item, long productOptionId, String name, String color, String size, long price) {
        item.setProductOptionId(productOptionId);
        item.setProductName(name);
        item.setColor(color);
        item.setSize(size);
        item.setPrice(price);
    }

    private void order(Order order, List<OrderItem> orderItemList, LocalDateTime time) {
        order.setOrderItemList(orderItemList);
        order.setCreateAt(time);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void top5Test() {
        //given
        OrderItem orderItem1 = new OrderItem();
        orderItem(orderItem1,  100, "블라우스", "pink", "M", 30000);

        OrderItem orderItem2 = new OrderItem();
        orderItem(orderItem2,  200, "셔츠", "yellow", "L", 35000);

        OrderItem orderItem3 = new OrderItem();
        orderItem(orderItem3,  300, "팬츠", "black", "S", 40000);

        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(orderItem1);
        orderItemList.add(orderItem2);
        orderItemList.add(orderItem3);

        OrderItem orderItem4 = new OrderItem();
        orderItem(orderItem4,  100, "블라우스", "pink", "M", 30000);

        OrderItem orderItem5 = new OrderItem();
        orderItem(orderItem5,  200, "셔츠", "yellow", "L", 35000);

        OrderItem orderItem6 = new OrderItem();
        orderItem(orderItem6,  300, "팬츠", "black", "S", 40000);

        List<OrderItem> orderItemList2 = new ArrayList<>();
        orderItemList2.add(orderItem4);
        orderItemList2.add(orderItem5);
        orderItemList2.add(orderItem6);

        List<OrderItem> orderItems = orderItemRepositoryImpl.saveAll(orderItemList);
        List<OrderItem> orderItems2 = orderItemRepositoryImpl.saveAll(orderItemList2);



        Order order1 = new Order();
        order(order1, orderItems, LocalDateTime.now().minusDays(2));

        Order order2 = new Order();
        order(order2, orderItems2, LocalDateTime.now().minusDays(3));

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);

        List<Order> orders1 = orderRepositoryImpl.saveAll(orders);
        System.out.println(orders1.size());

        //when
        List<Order> order3days = orderRepositoryImpl.getOrder3days();

        //then
        System.out.println(order3days);
    }
}