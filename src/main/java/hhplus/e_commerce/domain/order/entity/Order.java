package hhplus.e_commerce.domain.order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "`ORDER`")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long id;

    /**
     * 고객
     */
    @Column(name = "customer_id")
    private long customerId;

    /**
     * 주문 디테일 리스트
     */
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItemList = new ArrayList<>();


    /**
     * 날짜
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Order(long customerId) {
        this.customerId = customerId;
        this.createdAt = LocalDateTime.now();
    }
}
