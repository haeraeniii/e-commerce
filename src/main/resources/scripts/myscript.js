import http from 'k6/http';
import { check } from 'k6';
import {randomIntBetween} from 'https://jslib.k6.io/k6-utils/1.2.0/index.js';

export let options = {
    scenarios: {
        order_scenario: {
            vus: 100, // 가상 사용자
            exec: 'order_scenario',
            executor: 'per-vu-iterations', // 각각의 가상 사용자들이 정확한 반복 횟수만큼 실행
            iterations: 1000,
        }
    }
};

export function order_scenario() {

    getProduct(); // 상품 조트

    order(); // 주문 생성

}

function getProduct() {
    let productId = randomIntBetween(1, 5);

    let allProductRes = http.get(
        'http://localhost:8080/api/products/productDetail/' + productId,
        {
            tags: {name: 'all-product'}
        }
    )

    check(allProductRes, {'is status 200': (r) => r.status === 200})
}

function order() {
    let productId = randomIntBetween(1, 5);

    let orderRequest =
        {
            customerId: randomIntBetween(1, 4),
            orderItemRequestDtoList: [{
                productId: productId,
                productOptionId: randomIntBetween(productId * 3 - 2, productId * 3),
                orderQuantity: 1
            }]
        }

    let orderRes = http.post(
        'http://localhost:8080/api/order/',
        JSON.stringify(orderRequest),
        {
            headers: {'Content-Type': 'application/json'},
            tags: {name: 'order'}
        }
    )

    check(orderRes, {'is status 200': (r) => r.status === 200})
}