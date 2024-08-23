# 비즈니스 로직과 트랜잭션의 범위를 이해하고 애플리케이션 이벤트를 통한 관심사 분리

______________

**<ins>"DB의 부하를 줄이고 속도 및 성능을 향상시키기 위해 문제를 해소해야 하는 비즈니스 로직 구분하기"</ins>**

## 주문하기 

### 기존 코드 형태
```
 /**
     * 주문하기
     */
    @Transactional
    public Order order(OrderCommand.Create command) throws CustomException {
        //재고 차감 & 재고 전부 충분할 시 토탈 금액 구하기
        OrderProductCommand.Create orderProductCommand = productService.deductStockAndTotalAmount(command.newOrderItemList());

        // 고객 잔액 차감
        customerService.useBalance(command.customerId(), orderProductCommand.totalPrice());

        // 주문 실행
        Order order = orderService.order(command.customerId(), orderProductCommand.orderProductOptionList());

        // 외부 데이터 플랫폼 전송
        dataPlatformClient.sendOrder(event);

        return order;
    }

```

### 기존 코드의 문제점 파악
1. 하나의 트랜잭션 내에서 여러 작업이 수행되면서 속도 저하의 원인이 된다.
2. 여러 서비스들의 강한 결합으로 인해 시스템이 복잡하다.
3. 도메인 간의 의존성이 높다.

### Application Event 사용하기

#### why?
1. 이벤트가 동시 발행되면서 하나의 트랜잭션에서 차례로 작업되던 것들이 동시 실행이 가능해지므로, 속도가 개선이 된다.
2. 서비스들의 결합을 낮추어 각각의 서비스들의 독립성이 높아진다.
3. 기능을 일부 수정해야 할 때 그 기능을 담당하는 모듈만을 교체하기 쉬워지기 때문에 유지보수가 용이해진다.

#### 주문 시나리오

주문 진행 -> 주문 생성 ->
1. **주문 성공 시나리오** : 상품 재고 확인 후 차감 -> 주문 이벤트 발행 -> 고객 잔액 차감 & 주문 실행 -> 트랜잭션 커밋 후 외부로 데이터 전송
2. **상품 재고 부족 시나리오** : 상품 재고 확인 -> 재고 부족 -> 롤백
3. **고객 잔액 부족 시나리오** : 고객 잔액 확인 -> 잔액 부족 -> 롤백
4. **외부로 데이터 전송 실패** 시나리오 : 상품 재고 확인 후 차감 -> 주문 이벤트 발행 -> 고객 잔액 차감 & 주문 실행 -> 트랜잭션 커밋 후 외부로 데이터 전송 실패 -> 리트라이

### 변경된 코드 형태

```
/**
 * 주문하기
 */
@Transactional
public void order(OrderCommand.Create command) throws CustomException {
    Order order = orderService.order(command);

    //재고 차감 & 재고 전부 충분할 시 토탈 금액 구하기
    OrderProductCommand.Create orderProductCommand = productService.deductStockAndTotalAmount(command.newOrderItemList());

    eventPublisher.publishEvent(new OrderEvent(order.getId(), command.customerId(), orderProductCommand));

}
```

#### CustomerServiceListener 생성

```
@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
public void handlerOrderEvent(OrderEvent event) throws CustomException {
    log.info("고객 잔액 차감 실행");
    customerService.useBalance(event.getCustomerId(), event.getOrderProduct().totalPrice());
}
```

#### OrderEventListener 생성

```
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
```