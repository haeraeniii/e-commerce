package hhplus.e_commerce.domain.order.service;

import hhplus.e_commerce.support.exception.CustomException;
import hhplus.e_commerce.domain.order.entity.Order;
import hhplus.e_commerce.domain.order.entity.OrderItem;
import hhplus.e_commerce.domain.order.service.repository.OrderRepository;
import hhplus.e_commerce.domain.product.entity.ProductOption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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


    private void orderItem(OrderItem item, long productOptionId, String name, String color, String size, long price) {
        item.builder().productOptionId(productOptionId).productName(name).color(color).size(size).price(price).build();
    }

    private void dummyData(List<Order> orders) {
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

        Order order1 = new Order(1);

        Order order2 = new Order(1);

        orders.add(order1);
        orders.add(order2);
    }

    @Test
    @DisplayName("주문하기 - 재고 차감 테스트")
    public void orderTest() {
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

        ProductOption productOption1 = ProductOption.builder()
                .color("pink")
                .size("M")
                .stock(10)
                .build();

        ProductOption productOption2 = ProductOption.builder()
                .color("pink")
                .size("M")
                .stock(10)
                .build();

        ProductOption productOption3 = ProductOption.builder()
                .color("pink")
                .size("M")
                .stock(10)
                .build();

        List<ProductOption> productOptionList = new ArrayList<>();
        productOptionList.add(productOption1);
        productOptionList.add(productOption2);
        productOptionList.add(productOption3);

        orderItemList.forEach(orderItem -> {
            productOptionList.forEach(option -> {

                if(orderItem.getProductOptionId() == option.getId()) {
                    try {
                        option.deductStock(orderItem.getOrderQuantity());
                    } catch (CustomException e) {
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


}