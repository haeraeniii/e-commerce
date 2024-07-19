package hhplus.e_commerce.domain.product.service.dto;

import java.util.List;

public class ProductCommand {
    public record Create (
            String title,
            long price,
            List<NewProductOption> newProductOptionList
    ) {
        public record NewProductOption (
                String color,
                String size,
                long stock
        ) {}
    }
}
