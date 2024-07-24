package hhplus.e_commerce.domain.order.facade;

import hhplus.e_commerce.domain.customer.service.CustomerService;
import hhplus.e_commerce.domain.order.entity.Order;
import hhplus.e_commerce.domain.order.service.OrderService;
import hhplus.e_commerce.domain.order.service.command.OrderCommand;
import hhplus.e_commerce.domain.product.service.ProductService;
import hhplus.e_commerce.domain.product.service.command.OrderProductCommand;
import hhplus.e_commerce.exception.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
        OrderProductCommand.Create orderProductCommand = productService.deductStockAndTotalAmount(command.newOrderItemList());

        // 고객 잔액 차감
        customerService.useBalance(command.customerId(), orderProductCommand.totalPrice());

        return orderService.order(command.customerId(), orderProductCommand.orderProductOptionList());
    }
}
