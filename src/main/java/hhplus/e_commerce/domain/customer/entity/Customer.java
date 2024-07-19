package hhplus.e_commerce.domain.customer.entity;

import hhplus.e_commerce.base.exception.CustomException;
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
     * 고객 이름
     */
    private String name;

    /**
     * 잔액
     */
    private long balance;

    /**
     * 잔액 사용
     * @param balance
     * @throws CustomException
     */
    public void useBalance(long balance) throws CustomException {
        if(this.balance < balance) {
            throw new CustomException(CustomException.ExceptionType.BALANCE_SHORTAGE);
        }
        this.balance = this.balance - balance;
    }
}
