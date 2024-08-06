package hhplus.e_commerce.domain.product.service;

import hhplus.e_commerce.domain.order.entity.Order;
import hhplus.e_commerce.domain.order.entity.OrderItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;


@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @InjectMocks
    private ProductService productService;

    private void orderItem(OrderItem item, long productOptionId, String name, String color, String size, long price, long orderQuantity) {
        item.builder().productOptionId(productOptionId).productName(name).color(color).size(size).price(price).orderQuantity(orderQuantity).build();
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

        OrderItem orderItem7 = new OrderItem();
        orderItem(orderItem7,  300, "팬츠", "black", "M", 40000, 1);

        OrderItem orderItem8 = new OrderItem();
        orderItem(orderItem8,  400, "팬츠", "black", "L", 40000, 1);

        OrderItem orderItem9 = new OrderItem();
        orderItem(orderItem9,  400, "팬츠", "black", "L", 40000, 1);

        OrderItem orderItem10 = new OrderItem();
        orderItem(orderItem10,  400, "팬츠", "black", "L", 40000, 1);

        OrderItem orderItem11 = new OrderItem();
        orderItem(orderItem11,  500, "팬츠", "black", "L", 40000, 1);

        OrderItem orderItem12 = new OrderItem();
        orderItem(orderItem12,  600, "팬츠", "black", "L", 40000, 1);

        OrderItem orderItem13 = new OrderItem();
        orderItem(orderItem13,  700, "팬츠", "black", "L", 40000, 1);

        OrderItem orderItem14 = new OrderItem();
        orderItem(orderItem14,  700, "팬츠", "black", "L", 40000, 1);

        List<OrderItem> orderItemList2 = new ArrayList<>();
        orderItemList2.add(orderItem4);
        orderItemList2.add(orderItem5);
        orderItemList2.add(orderItem6);
        orderItemList2.add(orderItem7);
        orderItemList2.add(orderItem8);
        orderItemList2.add(orderItem9);
        orderItemList2.add(orderItem10);
        orderItemList2.add(orderItem11);
        orderItemList2.add(orderItem12);
        orderItemList2.add(orderItem13);
        orderItemList2.add(orderItem14);

        Order order1 = new Order(1);

        Order order2 = new Order(1);

        orders.add(order1);
        orders.add(order2);
    }

    @Test
    public void getProductTop5Test() {
        //given
        List<Order> orders = new ArrayList<>();
        dummyData(orders);

        List<OrderItem> orderItemList = new ArrayList<>();

        orders.forEach(order -> {
            orderItemList.addAll(order.getOrderItemList());
        });

        // 1. 상품 옵션 아이디 갯수를 취합해야함
        // 2. 해당 상품의 판매 수량을 체크해야함


        List<Long> tempOptionList = new ArrayList<>();

        orderItemList.forEach(orderItem -> {
            tempOptionList.add(orderItem.getProductOptionId());
        });


        Map<Long, Integer> map = new HashMap<Long, Integer>();

        for (long num : tempOptionList) {
            map.merge(num, 1, Integer::sum);
        }

        List<Long> tempOptionList2 = new ArrayList<>();

        int index = 0;

        List<Map.Entry<Long, Integer>> entryList = new LinkedList<>(map.entrySet());
        entryList.sort((o1, o2) -> map.get(o2.getKey()) - map.get(o1.getKey()));
        for(Map.Entry<Long, Integer> entry : entryList){
            System.out.println("key : " + entry.getKey() + ", value : " + entry.getValue());
            if(index < 5) {
                tempOptionList2.add(entry.getKey());
            }

            index++;
        }

        System.out.println(tempOptionList2);

        assertThat(tempOptionList2.size()).isEqualTo(5);
    }
}