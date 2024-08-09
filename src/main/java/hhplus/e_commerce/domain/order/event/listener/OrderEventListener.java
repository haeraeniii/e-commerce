package hhplus.e_commerce.domain.order.event.listener;

import hhplus.e_commerce.domain.order.client.DataPlatformClient;
import hhplus.e_commerce.domain.order.event.OrderEvent;
import hhplus.e_commerce.domain.order.service.OrderService;
import hhplus.e_commerce.support.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@AllArgsConstructor
public class OrderEventListener {

    private OrderService orderService;
    private DataPlatformClient dataPlatformClient;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handlerOrder(OrderEvent event) throws CustomException {
        log.info("주문 실행", event);
        orderService.order(event.getOrderId(), event.getOrderProduct().orderProductOptionList());
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendOrderInfoHandler(OrderEvent event) throws InterruptedException {
        log.info("외부 데이터 플랫폼에 주문 정보 전송", event);
        dataPlatformClient.sendOrder(event);
    }
}
