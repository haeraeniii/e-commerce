package hhplus.e_commerce.domain.order.facade;

import hhplus.e_commerce.domain.order.entity.Order;
import hhplus.e_commerce.domain.order.event.OrderEvent;
import hhplus.e_commerce.domain.order.service.OrderService;
import hhplus.e_commerce.domain.order.service.command.OrderCommand;
import hhplus.e_commerce.domain.product.service.ProductService;
import hhplus.e_commerce.domain.product.service.command.OrderProductCommand;
import hhplus.e_commerce.support.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@AllArgsConstructor
public class OrderProductFacade {
    private final OrderService orderService;
    private final ProductService productService;

    private final ApplicationEventPublisher eventPublisher;

    /**
     * 주문하기
     */
    @Transactional
    public void order(OrderCommand.Create command) throws CustomException {
        Order order = orderService.order(command);

        //재고 차감 & 재고 전부 충분할 시 토탈 금액 구하기
        OrderProductCommand.Create orderProductCommand = productService.deductStockAndTotalAmount(command.newOrderItemList());

        eventPublisher.publishEvent(new OrderEvent(order.getId(), command.customerId(), orderProductCommand));

    }
}
