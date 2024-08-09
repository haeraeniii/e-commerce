package hhplus.e_commerce.domain.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
public class OrderSheet {
    @Id
    @GeneratedValue
    @Column(name = "order_sheet_id")
    private long id;

    /**
     * 고객
     */
    @Column(name = "customer_id")
    private long customerId;

    /**
     * 상품 리스트
     */
    @OneToMany(mappedBy = "orderSheet")
    private List<OrderItemSheet> orderItemSheetList;

    /**
     * 상품 주문 시도 시간
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
