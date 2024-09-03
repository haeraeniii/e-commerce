package hhplus.e_commerce.domain.order.client;


import hhplus.e_commerce.domain.order.event.OrderEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DataPlatformClient {

    private RestTemplate restTemplate;

    public void DataPlatformMockApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // 외부 API에 주문 정보를 전송하는 메소드
    public void sendOrder(OrderEvent orderEvent) {
        String url = "https://api.external.com/orders";
        try {
            restTemplate.postForObject(url, orderEvent, Void.class);
        } catch (Exception e) {
            // 예외 처리: 로그 기록 또는 다른 에러 처리
            System.err.println("Failed to send order: " + e.getMessage());
        }
    }
}

