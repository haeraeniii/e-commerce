package hhplus.e_commerce.domain.product.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
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
     * 상품 옵션 리스트
     */
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductOption> productOptionList = new ArrayList<>();


    public Product(String title) {
        this.title = title;
    }
}
