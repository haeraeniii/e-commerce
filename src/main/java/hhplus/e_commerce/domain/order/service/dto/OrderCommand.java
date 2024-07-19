package hhplus.e_commerce.domain.order.service.dto;

import java.util.List;

public class OrderCommand {
    public record Create(
        long customerId,
        List<NewOrderItem> newOrderItemList
    ) {
        public record NewOrderItem(
            long productId,
            long productOptionId,
            long quantity
        ) {}
    }
}
