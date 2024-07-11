package hhplus.e_commerce.domain.customer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class OrderSheet {
    @Id
    @GeneratedValue
    private long id;

    /**
     * 고객
     */
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /**
     * 상품 옵션
     */
    private long productOptionId;

    /**
     * 상품명
     */
    private String title;

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
     * 상품 주문 시도 시간
     */
    private LocalDateTime orderTrialTime;
}
