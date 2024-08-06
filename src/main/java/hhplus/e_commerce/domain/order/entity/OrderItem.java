package hhplus.e_commerce.domain.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * 주문
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    @ToString.Exclude
    private Order order;

    /**
     * 상품 아이디
     */
    private long productId;

    /**
     * 상품 옵션 아이디
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

    /**
     * 주문 날짜
     */
    private LocalDateTime orderedAt;

    @Builder
    public OrderItem(Order order, long productId, long productOptionId, String productName, String color, String size, long price, long orderQuantity) {
        this.order = order;
        this.productId = productId;
        this.productOptionId = productOptionId;
        this.productName = productName;
        this.color = color;
        this.size = size;
        this.price = price;
        this.orderQuantity = orderQuantity;
        this.orderedAt = order.getCreatedAt();
        order.getOrderItemList().add(this);
    }
}
