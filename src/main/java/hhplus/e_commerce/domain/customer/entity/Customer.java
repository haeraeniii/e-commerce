package hhplus.e_commerce.domain.customer.entity;

import hhplus.e_commerce.base.exception.OrderException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private long id;

    /**
     * 잔액
     */
    private long balance;


    /**
     * 주문서
     */
    @OneToOne(mappedBy = "customer")
    private OrderSheet orderSheet;

    /**
     * 금액 충전
     * @param amount
     */
    public void chargeAmount(long amount) {
        this.balance = this.balance + amount;
    }

    /**
     * 잔액 사용
     * @param balance
     * @throws OrderException
     */
    public void useBalance(long balance) throws OrderException {
        if(this.balance < balance) {
            throw new OrderException(OrderException.ExceptionType.BALANCE_SHORTAGE, "잔액이 부족합니다.");
        }
        this.balance = this.balance - balance;
    }
}
