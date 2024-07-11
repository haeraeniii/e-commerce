package hhplus.e_commerce.domain.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Product {

    /**
     * 상품 아이디
     */
    @Id @GeneratedValue
    @Column(name = "product_id")
    private long id;

    /**
     * 상품 이름
     */
    private String title;

    /**
     * 상품 가격
     */
    private long price;

    /**
     * 상품 옵션 리스트
     */
    @OneToMany(mappedBy = "product")
    private List<ProductOption> productOptionList;
}
