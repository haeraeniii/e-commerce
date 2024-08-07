package hhplus.e_commerce.domain.order.service;

import hhplus.e_commerce.domain.product.service.command.OrderProductCommand;
import hhplus.e_commerce.support.exception.CustomException;
import hhplus.e_commerce.domain.order.entity.Order;
import hhplus.e_commerce.domain.order.entity.OrderItem;
import hhplus.e_commerce.domain.order.entity.OrderItemSheet;
import hhplus.e_commerce.domain.order.entity.OrderSheet;
import hhplus.e_commerce.domain.order.service.command.OrderCommand;
import hhplus.e_commerce.domain.order.service.repository.OrderItemRepository;
import hhplus.e_commerce.domain.order.service.repository.OrderItemSheetRepository;
import hhplus.e_commerce.domain.order.service.repository.OrderRepository;
import hhplus.e_commerce.domain.order.service.repository.OrderSheetRepository;
import hhplus.e_commerce.domain.product.entity.ProductOption;
import hhplus.e_commerce.domain.product.service.repository.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderSheetRepository orderSheetRepository;
    private final OrderItemSheetRepository orderItemSheetRepository;
    private final ProductOptionRepository productOptionRepository;

    /**
     * 주문서 생성
     */
    @CachePut(cacheNames = "orderSheet", key = "#result.id")
    public OrderSheet createOrderSheet (OrderCommand.Create command) {
        // 1. 상품 옵션 리스트 가져오기
        List<ProductOption> productOptionList =
            productOptionRepository.findAllById(
                    command.newOrderItemList().stream()
                            .map(OrderCommand.Create.NewOrderItem::productOptionId).toList()
            );

        // 2. 주문서 생성
        OrderSheet orderSheet = new OrderSheet();
        orderSheet.setCustomerId(command.customerId());
        orderSheet.setCreatedAt(LocalDateTime.now());

        OrderSheet sheet = orderSheetRepository.save(orderSheet);

        List<OrderItemSheet> itemList = productOptionList.stream().map(it -> {
            OrderItemSheet orderItemSheet = new OrderItemSheet();
            orderItemSheet.setOrderSheet(sheet);
            orderItemSheet.setProductOptionId(it.getId());
            orderItemSheet.setProductName(it.getProduct().getTitle());
            orderItemSheet.setColor(it.getColor());
            orderItemSheet.setSize(it.getSize());
            orderItemSheet.setPrice(it.getPrice());

            command.newOrderItemList().forEach(item -> {
                if(item.productOptionId() == it.getId()) {
                    orderItemSheet.setOrderQuantity(item.quantity());
                }
            });

            return orderItemSheet;
        }).toList();

        List<OrderItemSheet> orderItemSheets = orderItemSheetRepository.saveAll(itemList);

        sheet.setOrderItemSheetList(orderItemSheets);

        return sheet;
    }

    /**
     * 주문하기
     */
    @Transactional
//    @CachePut(cacheNames = "order", key = "#result.id")
    public Order order(long customerId, List<OrderProductCommand.Create.OrderProductOption> orderProductOptionList) throws CustomException {
        // 주문 내역 생성
        Order order = new Order(customerId);
        Order order1 = orderRepository.save(order);

        // 주문 아이템 내역 저장
        List<OrderItem> orderItemList = orderProductOptionList.stream().map(it -> {

            OrderItem orderItem = OrderItem.builder()
                    .order(order1)
                    .productId(it.productId())
                    .productOptionId(it.productOptionId())
                    .productName(it.title())
                    .color(it.color())
                    .size(it.size())
                    .price(it.price())
                    .orderQuantity(it.orderQuantity())
                    .build();

            return orderItem;
        }).toList();

        orderItemRepository.saveAll(orderItemList);

        return order1;
    }

    /**
     * 주문 내역 리스트 불러오기
     */
    public List<Order> getOrderList() {
        return orderRepository.getOrderList();
    }
}
