package hhplus.e_commerce.domain.order.event;

import hhplus.e_commerce.domain.product.service.command.OrderProductCommand;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class OrderEvent {
    private long orderId;
    private long customerId;
    private OrderProductCommand.Create orderProduct;

    public OrderEvent(long orderId, long customerId, OrderProductCommand.Create orderProduct) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderProduct = orderProduct;
    }
}
