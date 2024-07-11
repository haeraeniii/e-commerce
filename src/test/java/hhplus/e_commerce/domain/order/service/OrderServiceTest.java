package hhplus.e_commerce.domain.order.service;

import hhplus.e_commerce.base.exception.OrderException;
import hhplus.e_commerce.domain.order.entity.Order;
import hhplus.e_commerce.domain.order.entity.OrderItem;
import hhplus.e_commerce.domain.order.service.repository.OrderItemRepository;
import hhplus.e_commerce.domain.order.service.repository.OrderRepository;
import hhplus.e_commerce.domain.product.entity.ProductOption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;


    private void orderItem(OrderItem item, long productOptionId, String name, String color, String size, long price, long orderQuantity) {
        item.setProductOptionId(productOptionId);
        item.setProductName(name);
        item.setColor(color);
        item.setSize(size);
        item.setPrice(price);
        item.setOrderQuantity(orderQuantity);
    }

    private void order(Order order, List<OrderItem> orderItemList, LocalDateTime time) {
        order.setOrderItemList(orderItemList);
        order.setCreateAt(time);
    }

    private void dummyData(List<Order> orders) {
        OrderItem orderItem1 = new OrderItem();
        orderItem(orderItem1,  100, "블라우스", "pink", "M", 30000, 1);

        OrderItem orderItem2 = new OrderItem();
        orderItem(orderItem2,  200, "셔츠", "yellow", "L", 35000, 1);

        OrderItem orderItem3 = new OrderItem();
        orderItem(orderItem3,  300, "팬츠", "black", "S", 40000, 1);

        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(orderItem1);
        orderItemList.add(orderItem2);
        orderItemList.add(orderItem3);

        OrderItem orderItem4 = new OrderItem();
        orderItem(orderItem4,  100, "블라우스", "pink", "M", 30000, 1);

        OrderItem orderItem5 = new OrderItem();
        orderItem(orderItem5,  200, "셔츠", "yellow", "L", 35000, 1);

        OrderItem orderItem6 = new OrderItem();
        orderItem(orderItem6,  300, "팬츠", "black", "S", 40000, 1);

        List<OrderItem> orderItemList2 = new ArrayList<>();
        orderItemList2.add(orderItem4);
        orderItemList2.add(orderItem5);
        orderItemList2.add(orderItem6);

        Order order1 = new Order();
        order(order1, orderItemList, LocalDateTime.now().minusDays(4));

        Order order2 = new Order();
        order(order2, orderItemList2, LocalDateTime.now().minusDays(3));

        orders.add(order1);
        orders.add(order2);
    }

    @Test
    @DisplayName("주문하기 - 재고 차감 테스트")
    public void orderTest() {
        //given
        OrderItem orderItem1 = new OrderItem();
        orderItem(orderItem1,  100, "블라우스", "pink", "M", 30000, 1);

        OrderItem orderItem2 = new OrderItem();
        orderItem(orderItem2,  200, "셔츠", "yellow", "L", 35000, 1);

        OrderItem orderItem3 = new OrderItem();
        orderItem(orderItem3,  300, "팬츠", "black", "S", 40000,1);

        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(orderItem1);
        orderItemList.add(orderItem2);
        orderItemList.add(orderItem3);

        ProductOption productOption1 = new ProductOption();
        productOption1.setId(100);
        productOption1.setTitle("블라우스");
        productOption1.setColor("pink");
        productOption1.setSize("M");
        productOption1.setStock(10);

        ProductOption productOption2 = new ProductOption();
        productOption1.setId(200);
        productOption1.setTitle("셔츠");
        productOption1.setColor("yellow");
        productOption1.setSize("L");
        productOption1.setStock(10);

        ProductOption productOption3 = new ProductOption();
        productOption1.setId(300);
        productOption1.setTitle("팬츠");
        productOption1.setColor("black");
        productOption1.setSize("S");
        productOption1.setStock(10);

        List<ProductOption> productOptionList = new ArrayList<>();
        productOptionList.add(productOption1);
        productOptionList.add(productOption2);
        productOptionList.add(productOption3);

        orderItemList.forEach(orderItem -> {
            productOptionList.forEach(option -> {

                if(orderItem.getProductOptionId() == option.getId()) {
                    try {
                        option.subtractStock(orderItem.getOrderQuantity());
                    } catch (OrderException e) {
                        throw new RuntimeException(e);
                    }
                }

            });
        });

        //when
        System.out.println("stock" + productOptionList.get(0).getStock());

        //then
    }

    @Test
    @DisplayName("주문 내역 리스트 조회")
    public void getOrderListTest() {
        //given
        List<Order> orders = new ArrayList<>();
        dummyData(orders);

        //when
        when(orderRepository.getOrderList()).thenReturn(orders);

        //then
        assertThat(orderService.getOrderList().size()).isEqualTo(orders.size());
    }

    @Test
    @DisplayName("최근 3일간 판매 내역 조회")
    public void order3daysTest() {
        //given
        OrderItem orderItem1 = new OrderItem();
        orderItem(orderItem1,  100, "블라우스", "pink", "M", 30000, 1);

        OrderItem orderItem2 = new OrderItem();
        orderItem(orderItem2,  200, "셔츠", "yellow", "L", 35000, 1);

        OrderItem orderItem3 = new OrderItem();
        orderItem(orderItem3,  300, "팬츠", "black", "S", 40000,1);

        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(orderItem1);
        orderItemList.add(orderItem2);
        orderItemList.add(orderItem3);

        Order order1 = new Order();
        order(order1, orderItemList, LocalDateTime.now().minusDays(3));

        List<Order> orders = new ArrayList<>();

        orders.add(order1);

        //when
        when(orderRepository.getOrder3days()).thenReturn(orders);

        //then
        assertThat(orderService.getOrder3days().size()).isEqualTo(1);
    }
}