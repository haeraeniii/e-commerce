package hhplus.e_commerce.domain.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class OrderItemSheet {
    @Id
    @GeneratedValue
    private long id;

    /**
     * 주문서
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_sheet_id")
    @JsonIgnore
    private OrderSheet orderSheet;

    /**
     * 상품 아이디
     */
    private long productId;

    /**
     * 상품 옵션
     */
    private long productOptionId;

    /**
     * 상품명
     */
    private String productName;

    /**
     * 상품 컬러
     */
    private String color;

    /**
     * 상품 사이즈
     */
    private String size;

    /**
     * 상품 가격
     */
    private long price;

    /**
     * 주문 수량
     */
    private long orderQuantity;
}