package hhplus.e_commerce.domain.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "`ORDER`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long id;

    /**
     * 고객
     */
    private long customerId;

    /**
     * 주문 디테일 리스트
     */
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItemList;


    /**
     * 날짜
     */
    private LocalDateTime createAt;
}
