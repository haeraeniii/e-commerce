package hhplus.e_commerce.domain.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import hhplus.e_commerce.base.exception.CustomException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
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
     * 상품 재고
     */
    private long stock;

    /**
     * 상품
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    /**
     * 재고 차감
     * @param stock
     * @throws CustomException
     */
    public void subtractStock (long stock) throws CustomException {
        if(this.stock <= 0) {
            throw new CustomException(CustomException.ExceptionType.STOCK_SHORTAGE);
        }
        this.stock = this.stock - stock;
    }

    /**
     * 재고 복귀
     * @param stock
     */
    public void addStock (long stock) {
        this.stock = this.stock + stock;
    }
}
