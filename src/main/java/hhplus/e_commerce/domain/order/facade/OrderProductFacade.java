package hhplus.e_commerce.domain.order.facade;

import hhplus.e_commerce.domain.customer.service.CustomerService;
import hhplus.e_commerce.domain.order.entity.Order;
import hhplus.e_commerce.domain.order.service.OrderService;
import hhplus.e_commerce.domain.order.service.command.OrderCommand;
import hhplus.e_commerce.domain.product.service.ProductService;
import hhplus.e_commerce.domain.product.service.command.OrderProductCommand;
import hhplus.e_commerce.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@AllArgsConstructor
public class OrderProductFacade {
    private final OrderService orderService;
    private final ProductService productService;
    private final CustomerService customerService;

    /**
     * 주문하기
     */
    @Transactional
    public Order order(OrderCommand.Create command) throws CustomException {
        //재고 차감 & 재고 전부 충분할 시 토탈 금액 구하기
        log.info("재고 확인 & 차감 로직 진행");
        OrderProductCommand.Create orderProductCommand = productService.deductStockAndTotalAmount(command.newOrderItemList());
        log.info("totalPrice : {}", orderProductCommand.totalPrice());

        // 고객 잔액 차감
        log.info("고객 잔액 차감 로직 진행");
        customerService.useBalance(command.customerId(), orderProductCommand.totalPrice());


        log.info("오더 로직 진행");
        return orderService.order(command.customerId(), orderProductCommand.orderProductOptionList());
    }
}
