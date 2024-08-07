package hhplus.e_commerce.domain.customer.entity;

import hhplus.e_commerce.support.exception.CustomException;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long id;

    /**
     * 고객 이름
     */
    @Column(name = "customer_name")
    private String customerName;

    /**
     * 잔액
     */
    private long balance;

    public Customer(String name) {
        this.customerName = name;
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
        System.out.println("useBalance_check " + balance + "," + this.getBalance() + ", " + this.balance);
        if(this.balance < balance) {
            throw new CustomException(CustomException.ExceptionType.BALANCE_SHORTAGE, "잔액이 부족합니다.");
        }
        this.balance = this.balance - balance;
    }
}
