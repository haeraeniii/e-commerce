package hhplus.e_commerce.domain.cart.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
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
}
