package hhplus.e_commerce.domain.product.service;

import hhplus.e_commerce.domain.order.entity.Order;
import hhplus.e_commerce.domain.order.entity.OrderItem;
import hhplus.e_commerce.domain.order.service.repository.OrderRepository;
import hhplus.e_commerce.domain.product.entity.Product;
import hhplus.e_commerce.domain.product.entity.ProductOption;
import hhplus.e_commerce.domain.product.service.repository.ProductOptionRepository;
import hhplus.e_commerce.domain.product.service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final OrderRepository orderRepository;

    // 상품 목록 조회
    public List<Product> getProductList () {
        return productRepository.getProductList();
    }

    // 상품 상세 조회
    public Product getProductDetail (long id) {
        return productRepository.getProduct(id);
    }

    // 인기 상품 Top5 조회
    // 1. 최근 3일 동안의 주문 내역 불러오기
    // 2. 주문 내역 내에서 주문한 상품들 불러오기
    // 3. 주문한 상품들 중복 갯수 체크 & 갯수 높은 순으로 나열
    public List<Product> getProductTop5 () {
        // 최근 3일간의 주문 내역 불러오기
        List<Order> order3days = orderRepository.getOrder3days();

        // 주문한 아이템들 리스트 뽑기
        List<OrderItem> orderItemList = new ArrayList<>();
        order3days.forEach(order -> {
            orderItemList.addAll(order.getOrderItemList());
        });

        // 상품 옵션 아이디로 리스트 만들기
        List<Long> tempOptionList = new ArrayList<>();
        orderItemList.forEach(orderItem -> {
            tempOptionList.add(orderItem.getProductOptionId());
        });

        // 상품 아이디로 리스트 만들기
        List<ProductOption> allById = productOptionRepository.findAllById(tempOptionList);
        List<Long> productIdList = new ArrayList<>();
        allById.forEach(productOption -> {
            productIdList.add(productOption.getProduct().getId());
        });

        // 리스트로 해쉬맵 만들기
        Map<Long, Integer> map = new HashMap<Long, Integer>();

        for (long num : productIdList) {
            map.merge(num, 1, Integer::sum);
        }

        // 가장 많이 팔린 순으로 나열해서 5개 뽑기
        List<Long> productList2 = new ArrayList<>();

        int index = 0;

        List<Map.Entry<Long, Integer>> entryList = new LinkedList<>(map.entrySet());
        entryList.sort((o1, o2) -> map.get(o2.getKey()) - map.get(o1.getKey()));
        for(Map.Entry<Long, Integer> entry : entryList){

            if(index < 5) {
                productList2.add(entry.getKey());
            }

            index++;
        }

        return productRepository.findAllById(productList2);
    }
}
