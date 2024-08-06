package hhplus.e_commerce.domain.product.service.command;

import java.util.List;

public class ProductCommand {
    public record Create (
            String title,
            List<NewProductOption> newProductOptionList
    ) {
        public record NewProductOption (
                String color,
                String size,
                long stock,
                long price
        ) {}
    }
}
