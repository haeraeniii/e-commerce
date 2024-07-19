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
}
