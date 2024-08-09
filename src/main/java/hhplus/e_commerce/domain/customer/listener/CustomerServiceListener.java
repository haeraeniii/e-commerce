package hhplus.e_commerce.domain.customer.listener;

import hhplus.e_commerce.domain.customer.service.CustomerService;
import hhplus.e_commerce.domain.order.event.OrderEvent;
import hhplus.e_commerce.support.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@AllArgsConstructor
public class CustomerServiceListener {

    private CustomerService customerService;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handlerOrderEvent(OrderEvent event) throws CustomException {
        log.info("고객 잔액 차감 실행");
        customerService.useBalance(event.getCustomerId(), event.getOrderProduct().totalPrice());
    }
}
