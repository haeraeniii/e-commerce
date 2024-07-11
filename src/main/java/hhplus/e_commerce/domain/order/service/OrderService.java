package hhplus.e_commerce.domain.order.service;

import hhplus.e_commerce.base.exception.OrderException;
import hhplus.e_commerce.domain.customer.entity.Customer;
import hhplus.e_commerce.domain.customer.service.repository.CustomerRepository;
import hhplus.e_commerce.domain.order.entity.Order;
import hhplus.e_commerce.domain.order.entity.OrderItem;
import hhplus.e_commerce.domain.order.service.repository.OrderRepository;
import hhplus.e_commerce.domain.product.entity.ProductOption;
import hhplus.e_commerce.domain.product.service.repository.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductOptionRepository productOptionRepository;
    private final CustomerRepository customerRepository;

    /**
     * 주문하기
     */
    public Order order(Order order) {

        List<OrderItem> orderItemList = order.getOrderItemList();
        List<ProductOption> orderOptionList = new ArrayList<>();

        // 1. 상품의 재고가 있는지 확인
        // 2. 상품의 재고가 있을 경우 결제 요청
        // 3. 고객의 잔고가 부족할 경우 잔고부족 Exception

        // 2-1. 상품의 재고가 없을 경우 재고 없는 것을 알리고 재고 없음 Exception

        //주문 리스트에서 상품 옵션 뽑아내기
        orderItemList.forEach(orderItem -> {
            orderOptionList.add(productOptionRepository.getProductOption(orderItem.getProductOptionId()));
        });


        // 상품 옵션의 재고에서 주문 수량 차감 (재고 없을 시 Exception)
        orderItemList.forEach(orderItem -> {
            orderOptionList.forEach(option -> {

                if(orderItem.getProductOptionId() == option.getId()) {
                    try {
                        option.subtractStock(orderItem.getOrderQuantity());
                    } catch (OrderException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        });

        // 고객 잔액 차감 (잔액 부족시 Exception)
        Customer customer = customerRepository.getBalance(order.getCustomerId());
        orderItemList.forEach(orderItem -> {
            try {
                customer.useBalance(orderItem.getPrice());
            } catch (OrderException e) {
                throw new RuntimeException(e);
            }
        });

        // 재고 부족하거나 잔액 부족시 저장하지 않음
        productOptionRepository.saveAll(orderOptionList);
        customerRepository.save(customer);

        return orderRepository.save(order);
    }

    /**
     * 주문 내역 리스트 불러오기
     * @return
     */
    public List<Order> getOrderList() {
        return orderRepository.getOrderList();
    };

    /**
     * 최근 3일 주문 내역 불러오기
     * @return
     */
    public List<Order> getOrder3days() {
        return orderRepository.getOrder3days();
    }
}
