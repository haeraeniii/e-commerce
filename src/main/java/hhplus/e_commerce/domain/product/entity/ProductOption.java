package hhplus.e_commerce.domain.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hhplus.e_commerce.exception.CustomException;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductOption {

    /**
     * 상품 옵션 아이디
     */
    @Id @GeneratedValue
    @Column(name = "product_option_id")
    private long id;

    /**
     * 상품 컬러
     */
    private String color;

    /**
     * 상품 사이즈
     */
    private String size;

    /**
     * 상품 금액
     */
    private long price;

    /**
     * 상품 재고
     */
    private long stock;

    /**
     * 상품
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    @ToString.Exclude
    private Product product;

    @Builder
    public ProductOption(String color, String size, long price, long stock, Product product) {
        this.color = color;
        this.size = size;
        this.price = price;
        this.stock = stock;
        this.product = product;
        product.getProductOptionList().add(this);
    }

    /**
     * 재고 차감
     */
    public void deductStock (long stock) throws CustomException {
        if(this.stock <= 0) {
            throw new CustomException(CustomException.ExceptionType.STOCK_SHORTAGE);
        }
        this.stock -= stock;
    }

    /**
     * 재고 복귀
     */
    public void addStock (long stock) {
        this.stock += stock;
    }
}
