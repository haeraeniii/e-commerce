package hhplus.e_commerce.domain.product.service.command;

import java.util.List;

public class OrderProductCommand {
    public record Create(
            long totalPrice,
            List<OrderProductOption> orderProductOptionList
    ){
        public record OrderProductOption(
                long productId,
                long productOptionId,
                String title,
                String color,
                String size,
                long price,
                long orderQuantity
        ){}
    }
}
