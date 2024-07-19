package hhplus.e_commerce.domain.order.service;

import hhplus.e_commerce.base.exception.CustomException;
import hhplus.e_commerce.domain.customer.entity.Customer;
import hhplus.e_commerce.domain.customer.service.repository.CustomerRepository;
import hhplus.e_commerce.domain.order.entity.Order;
import hhplus.e_commerce.domain.order.entity.OrderItem;
import hhplus.e_commerce.domain.order.entity.OrderItemSheet;
import hhplus.e_commerce.domain.order.entity.OrderSheet;
import hhplus.e_commerce.domain.order.service.dto.OrderCommand;
import hhplus.e_commerce.domain.order.service.repository.OrderItemRepository;
import hhplus.e_commerce.domain.order.service.repository.OrderItemSheetRepository;
import hhplus.e_commerce.domain.order.service.repository.OrderRepository;
import hhplus.e_commerce.domain.order.service.repository.OrderSheetRepository;
import hhplus.e_commerce.domain.product.entity.Product;
import hhplus.e_commerce.domain.product.entity.ProductOption;
import hhplus.e_commerce.domain.product.service.repository.ProductOptionRepository;
import hhplus.e_commerce.domain.product.service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderSheetRepository orderSheetRepository;
    private final OrderItemSheetRepository orderItemSheetRepository;
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final CustomerRepository customerRepository;

    /**
     * 주문서 생성
     */
    public OrderSheet createOrderSheet (OrderCommand.Create command) {
        // 1. 상품 옵션 리스트 가져오기
        List<ProductOption> productOptionList =
            productOptionRepository.findAllById(
                    command.newOrderItemList().stream()
                            .map(it -> it.productOptionId()).toList()
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
            orderItemSheet.setPrice(it.getProduct().getPrice());

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
    public Order order(OrderCommand.Create command) throws CustomException {
        // 1. 상품 옵션 리스트 가져오기
        List<ProductOption> productOptionList = new ArrayList<>();


        // 2. 재고 차감
        command.newOrderItemList().forEach(item -> {
            try {
                ProductOption option = productOptionRepository.getById(item.productOptionId());
                option.subtractStock(item.quantity());
                productOptionList.add(option);

            } catch (CustomException e) {
                throw new RuntimeException(e);
            }
        });

        // 3. 재고 있으면 토탈 금액 구하기
        Long totalOrderAmount = command.newOrderItemList()
            .stream().map(it -> {
                Product product = productRepository.getProduct(it.productId());
                return it.quantity() * product.getPrice();
            }).mapToLong(i -> i).sum();

        // 고객 잔액 차감
        Customer customer = customerRepository.getCustomer(command.customerId());
        customer.useBalance(totalOrderAmount);

        // 주문 내역 생성
        Order order = new Order();
        order.setCustomerId(command.customerId());
        order.setCreatedAt(LocalDateTime.now());
        Order order1 = orderRepository.save(order);

        // 주문 아이템 내역 저장
        List<OrderItem> orderItemList = productOptionList.stream().map(it -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order1);
            orderItem.setProductId(it.getProduct().getId());
            orderItem.setProductOptionId(it.getId());
            orderItem.setProductName(it.getProduct().getTitle());
            orderItem.setColor(it.getColor());
            orderItem.setSize(it.getSize());
            orderItem.setPrice(it.getProduct().getPrice());

            command.newOrderItemList().forEach(item -> {
                if(item.productOptionId() == it.getId()) {
                    orderItem.setOrderQuantity(item.quantity());
                }
            });

            return orderItem;
        }).toList();

        List<OrderItem> orderItemList1 = orderItemRepository.saveAll(orderItemList);

        order1.setOrderItemList(orderItemList1);

        return order1;
    }

    /**
     * 주문 내역 리스트 불러오기
     */
    public List<Order> getOrderList() {
        return orderRepository.getOrderList();
    }
}
