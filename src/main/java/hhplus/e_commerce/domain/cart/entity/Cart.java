package hhplus.e_commerce.domain.cart.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id @GeneratedValue
    private long id;

    /**
     * 고객 아이디
     */
    private long customerId;

    /**
     * 상품 옵션 아이디
     */
    private long productOptionId;

    /**
     * 상품 이름
     */
    String productName;

    /**
     * 상품 컬러
     */
    String color;

    /**
     * 상품 사이즈
     */
    String size;

    /**
     * 상품 금액
     */
    long price;

    /**
     * 주문 수량
     */
    long orderQuantity;

    @Builder
    public Cart(long customerId, long productOptionId, String productName, String color, String size, long price, long orderQuantity) {
        this.customerId = customerId;
        this.productOptionId = productOptionId;
        this.productName = productName;
        this.color = color;
        this.size = size;
        this.price = price;
        this.orderQuantity = orderQuantity;
    }
}
