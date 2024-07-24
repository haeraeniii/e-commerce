package hhplus.e_commerce.domain.customer.entity;

import hhplus.e_commerce.exception.CustomException;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private long id;

    /**
     * 고객 이름
     */
    private String name;

    /**
     * 잔액
     */
    private long balance;

    @Builder
    public Customer(String name, long balance) {
        this.name = name;
        this.balance = balance;
    }

    /**
     * 금액 충전
     */
    public void charge(long amount) {
        this.balance += amount;
    }

    /**
     * 잔액 사용
     */
    public void useBalance(long balance) throws CustomException {
        if(this.balance < balance) {
            throw new CustomException(CustomException.ExceptionType.BALANCE_SHORTAGE);
        }
        this.balance = this.balance - balance;
    }
}
