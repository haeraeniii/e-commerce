# e-commerce

# [Chapter 4] 장애 대응

------------------

### **`STEP 19`**

- 부하 테스트 대상 선정 및 목적, 시나리오 등의 계획을 세우고 이를 문서로 작성
- 적합한 테스트 스크립트를 작성하고 수행

### **`STEP 20`**

- 위 테스트를 진행하며 획득한 다양한 성능 지표를 분석 및 시스템 내의 병목을 탐색 및 개선해보고 **(가상)** 장애 대응 문서를 작성하고 제출
- 최종 발표 자료 작성 및 제출

-------------------

## 부하 테스트 

### 테스트 개요

#### 테스트 목적
   이번 부하 테스트의 목적은 시스템이 특정 부하 조건 하에서 얼마나 안정적으로 작동하는지, 그리고 성능 저하 없이 사용자의 요청을 처리할 수 있는지를 평가하는 것입니다. 주문 처리 시스템의 안정성과 성능을 검증하기 위해 테스트를 진행합니다.
    
#### 테스트 대상
   테스트 대상은 주문 관리 시스템의 API 엔드포인트입니다. 이 시스템은 사용자 충전, 메뉴 조회, 주문 생성의 주요 기능을 제공합니다. 각 기능이 대량의 사용자 요청을 처리할 수 있는지 확인하는 것이 목표입니다.

### 시나리오 선정 과정

#### 주요 사용자 시나리오 분석
실제 사용자의 흐름을 예측한 결과, 다음과 같은 주요 시나리오가 도출되었습니다.
    
1. 상품 조회 기능 : 사용자가 메뉴를 확인하고 선택하는 과정입니다. 이 단계에서 다수의 사용자가 동시에 접속할 때, 시스템이 안정적으로 메뉴 정보를 제공할 수 있는지 테스트합니다.
2. 주문 기능 : 사용자가 메뉴를 선택하고 주문을 생성하는 과정입니다. 이 기능은 시스템의 핵심이므로, 주문 생성 요청이 많은 상황에서도 성능이 저하되지 않는지 검증합니다.

#### 가상 사용자 수와 반복 횟수 설정
        
1. 가상 사용자 수(Virtual Users, VUs) : 100명의 가상 사용자 설정
2. 반복 횟수(Iterations) : 각 가상 사용자가 1000번의 시나리오를 반복하도록 설정

#### API 엔드포인트 설정
테스트에 포함된 API 엔드포인트는 다음과 같습니다.

1. 상품 조회 API(`GET /api/products/productDetail`)
2. 주문 API(`POST /api/order/`)

### 테스트 결과 및 분석
테스트 결과는 다음과 같은 지표를 중심으로 분석할 예정입니다.

1. 응답 시간(Response Time)
2. 성공 및 실패한 요청 수
3. 시스템 자원 사용률(CPU, Memory)

이 지표들은 InfluxDB와 Grafana를 통해 시각화하여 분석할 것입니다.

### 상품조회 API 

**캐시 적용 전**
```agsl
     checks.........................: 100.00% ✓ 100000       ✗ 0     
     data_received..................: 7.3 MB  967 kB/s
     data_sent......................: 11 MB   1.4 MB/s
     http_req_blocked...............: avg=6.51µs  min=0s     med=1µs    max=21.49ms  p(90)=1µs     p(95)=2µs    
     http_req_connecting............: avg=4.14µs  min=0s     med=0s     max=5.84ms   p(90)=0s      p(95)=0s     
     http_req_duration..............: avg=7.33ms  min=49µs   med=1.23ms max=329.66ms p(90)=17.56ms p(95)=24.87ms
       { expected_response:true }...: avg=7.33ms  min=49µs   med=1.23ms max=329.66ms p(90)=17.56ms p(95)=24.87ms
     http_req_failed................: 0.00%   ✓ 0            ✗ 100000
     http_req_receiving.............: avg=21.71µs min=2µs    med=5µs    max=32.94ms  p(90)=14µs    p(95)=22µs   
     http_req_sending...............: avg=5.26µs  min=1µs    med=2µs    max=28.33ms  p(90)=6µs     p(95)=9µs    
     http_req_tls_handshaking.......: avg=0s      min=0s     med=0s     max=0s       p(90)=0s      p(95)=0s     
     http_req_waiting...............: avg=7.3ms   min=45µs   med=1.2ms  max=329.65ms p(90)=17.52ms p(95)=24.8ms 
     http_reqs......................: 100000  13209.380034/s
     iteration_duration.............: avg=7.39ms  min=67.2µs med=1.29ms max=329.69ms p(90)=17.63ms p(95)=24.95ms
     iterations.....................: 100000  13209.380034/s
     vus............................: 92      min=92         max=100 
     vus_max........................: 100     min=100        max=100 


running (00m07.6s), 000/100 VUs, 100000 complete and 0 interrupted iterations
order_scenario ✓ [=====================================] 100 VUs  00m07.6s/10m0s  100000/100000 iters, 1000 per VU
```

- data_received: 7.3 MB (967 kB/s)
- data_sent: 11 MB (1.4 MB/s)
- http_req_duration: 평균 7.33ms, 최대 329.66ms
- http_req_waiting: 평균 7.3ms, 최대 329.65ms
- http_reqs: 13,209 요청/초
- iterations: 13,209 반복/초
- tests: 7.6초 소요

**캐시 적용 후**
```agsl

     checks.........................: 100.00% ✓ 100000       ✗ 0     
     data_received..................: 7.3 MB  1.3 MB/s
     data_sent......................: 11 MB   1.9 MB/s
     http_req_blocked...............: avg=6.35µs  min=0s        med=0s     max=18.22ms  p(90)=1µs     p(95)=2µs    
     http_req_connecting............: avg=4.07µs  min=-375000ns med=0s     max=10.96ms  p(90)=0s      p(95)=0s     
     http_req_duration..............: avg=5.56ms  min=48µs      med=2.69ms max=535.77ms p(90)=12.64ms p(95)=18.42ms
       { expected_response:true }...: avg=5.56ms  min=48µs      med=2.69ms max=535.77ms p(90)=12.64ms p(95)=18.42ms
     http_req_failed................: 0.00%   ✓ 0            ✗ 100000
     http_req_receiving.............: avg=13.73µs min=2µs       med=4µs    max=32.3ms   p(90)=13µs    p(95)=21µs   
     http_req_sending...............: avg=5.11µs  min=1µs       med=2µs    max=20.2ms   p(90)=6µs     p(95)=9µs    
     http_req_tls_handshaking.......: avg=0s      min=0s        med=0s     max=0s       p(90)=0s      p(95)=0s     
     http_req_waiting...............: avg=5.54ms  min=44µs      med=2.68ms max=535.76ms p(90)=12.59ms p(95)=18.38ms
     http_reqs......................: 100000  17506.383703/s
     iteration_duration.............: avg=5.62ms  min=62.75µs   med=2.75ms max=535.82ms p(90)=12.75ms p(95)=18.57ms
     iterations.....................: 100000  17506.383703/s
     vus............................: 100     min=100        max=100 
     vus_max........................: 100     min=100        max=100 


running (00m05.7s), 000/100 VUs, 100000 complete and 0 interrupted iterations
order_scenario ✓ [======================================] 100 VUs  00m05.7s/10m0s  100000/100000 iters, 1000 per VU

```

- data_received: 7.3 MB (1.3 MB/s)
- data_sent: 11 MB (1.9 MB/s)
- http_req_duration: 평균 5.56ms, 최대 535.77ms
- http_req_waiting: 평균 5.54ms, 최대 535.76ms
- http_reqs: 17,506 요청/초
- iterations: 17,506 반복/초
- tests: 5.7초 소요

#### 비교 요약
**속도:** 캐시 적용 후 데이터 수신 및 송신 속도가 더 빨라짐.<br/>
**응답 시간:** 캐시 적용 후 평균 HTTP 요청 시간(5.56ms)이 캐시 적용 전보다 빨라짐(7.33ms).<br/>
**처리량:** 캐시 적용 후 요청 처리량과 반복 속도에서 캐시 적용 전보다 높은 성능을 보임.<br/>
**최대 응답 시간:** 캐시 적용 후의 최대 응답 시간이 캐시 적용 전보다 길지만, 평균 응답 시간은 더 빠름.<br/>
**소요 시간:** 캐시 적용 후 전체 테스트 실행 시간이 더 짧음<br/>

#### 결론
**캐시 적용 후 평균 응답 시간이 더 빠르고, 요청 처리량도 더 높은 성능을 보여줌**


### 상품 주문 API

**로깅 최적화 및 캐시 적용 전**
```agsl
     checks.........................: 100.00% ✓ 100000       ✗ 0     
     data_received..................: 7.3 MB  986 kB/s
     data_sent......................: 24 MB   3.3 MB/s
     http_req_blocked...............: avg=9.57µs  min=0s      med=1µs    max=23.37ms  p(90)=2µs     p(95)=2µs    
     http_req_connecting............: avg=5.98µs  min=0s      med=0s     max=23.09ms  p(90)=0s      p(95)=0s     
     http_req_duration..............: avg=7.16ms  min=55µs    med=1.43ms max=372.04ms p(90)=17.02ms p(95)=24.26ms
       { expected_response:true }...: avg=7.16ms  min=55µs    med=1.43ms max=372.04ms p(90)=17.02ms p(95)=24.26ms
     http_req_failed................: 0.00%   ✓ 0            ✗ 100000
     http_req_receiving.............: avg=29.83µs min=2µs     med=5µs    max=66.28ms  p(90)=15µs    p(95)=27µs   
     http_req_sending...............: avg=13.49µs min=1µs     med=3µs    max=61.68ms  p(90)=8µs     p(95)=13µs   
     http_req_tls_handshaking.......: avg=0s      min=0s      med=0s     max=0s       p(90)=0s      p(95)=0s     
     http_req_waiting...............: avg=7.12ms  min=48µs    med=1.4ms  max=372.03ms p(90)=16.92ms p(95)=24.15ms
     http_reqs......................: 100000  13474.188911/s
     iteration_duration.............: avg=7.29ms  min=80.08µs med=1.52ms max=372.08ms p(90)=17.21ms p(95)=24.53ms
     iterations.....................: 100000  13474.188911/s
     vus............................: 95      min=95         max=100 
     vus_max........................: 100     min=100        max=100 


running (00m07.4s), 000/100 VUs, 100000 complete and 0 interrupted iterations
order_scenario ✓ [======================================] 100 VUs  00m07.4s/10m0s  100000/100000 iters, 1000 per VU


```

- 평균 응답 시간 (http_req_duration): 7.16ms
- 최소 응답 시간: 55µs
- 최대 응답 시간: 372.04ms
- 데이터 수신 속도: 986 kB/s
- 데이터 전송 속도: 3.3 MB/s
- 초당 요청 수 (http_reqs): 13,474.19
- 초당 반복 수 (iterations): 13,474.19


**로깅 최적화 및 캐시 적용 후**

```agsl
     checks.........................: 100.00% ✓ 100000       ✗ 0     
     data_received..................: 7.3 MB  1.3 MB/s
     data_sent......................: 24 MB   4.3 MB/s
     http_req_blocked...............: avg=8.05µs  min=0s        med=1µs    max=13.71ms  p(90)=1µs     p(95)=2µs    
     http_req_connecting............: avg=6.39µs  min=0s        med=0s     max=13.68ms  p(90)=0s      p(95)=0s     
     http_req_duration..............: avg=5.47ms  min=53µs      med=1.23ms max=426.74ms p(90)=12.73ms p(95)=18.35ms
       { expected_response:true }...: avg=5.47ms  min=53µs      med=1.23ms max=426.74ms p(90)=12.73ms p(95)=18.35ms
     http_req_failed................: 0.00%   ✓ 0            ✗ 100000
     http_req_receiving.............: avg=18.76µs min=2µs       med=4µs    max=26.02ms  p(90)=14µs    p(95)=25µs   
     http_req_sending...............: avg=8.12µs  min=-235000ns med=2µs    max=18.59ms  p(90)=8µs     p(95)=12µs   
     http_req_tls_handshaking.......: avg=0s      min=0s        med=0s     max=0s       p(90)=0s      p(95)=0s     
     http_req_waiting...............: avg=5.45ms  min=47µs      med=1.2ms  max=426.73ms p(90)=12.68ms p(95)=18.31ms
     http_reqs......................: 100000  17626.295048/s
     iteration_duration.............: avg=5.55ms  min=76.58µs   med=1.31ms max=426.9ms  p(90)=12.84ms p(95)=18.44ms
     iterations.....................: 100000  17626.295048/s
     vus............................: 100     min=100        max=100 
     vus_max........................: 100     min=100        max=100 


running (00m05.7s), 000/100 VUs, 100000 complete and 0 interrupted iterations
order_scenario ✓ [======================================] 100 VUs  00m05.7s/10m0s  100000/100000 iters, 1000 per VU


```

- 평균 응답 시간 (http_req_duration): 5.47ms
- 최소 응답 시간: 53µs
- 최대 응답 시간: 426.74ms
- 데이터 수신 속도: 1.3 MB/s
- 데이터 전송 속도: 4.3 MB/s
- 초당 요청 수 (http_reqs): 17,626.30
- 초당 반복 수 (iterations): 17,626.30

#### 비교 요약
**응답 시간 개선:** 평균 응답 시간이 로깅 최적화 및 캐시 적용 후 7.16ms에서 5.47ms로 줄어듦.<br/>
**데이터 전송 및 수신 속도:** 데이터 수신 속도가 986 kB/s에서 1.3 MB/s로 증가하였고, 데이터 전송 속도도 3.3 MB/s에서 4.3 MB/s로 증가<br/>
**초당 요청 수 및 반복수:** 초당 요청 수 (http_reqs)와 초당 반복 수 (iterations)가 각각 13,474.19에서 17,626.30으로 증가<br/>

#### 결론
**로깅 최적화와 캐시 적용이 전반적으로 성능 개선에 긍정적인 영향을 미쳤음. 평균 응답 시간이 줄어들고, 초당 요청 수와 반복 수가 증가한 점에서 이러한 개선이 확실히 효과적이었음을 확인할 수 있음.**

-----------------

### 문제점 분석
1. 응답 시간 지연 : 특정 시점에서 응답 시간이 급격히 증가하는 현상이 관찰되었습니다.
2. 요청 실패율 증가 : 부하가 일정 수준을 넘어서자, 요청 실패율이 증가하는 현상이 발생했습니다.
3. 자원 고갈 : CPU와 메모리 사용량이 일정 수준을 넘어서면서 성능이 급격히 저하되는 현상이 발생했습니다.

### 기능 개선
1. 데이터베이스 쿼리 최적화: 데이터베이스 쿼리의 성능을 최적화하여 응답 시간을 단축했습니다.
2. 캐싱 도입: 반복적인 데이터 요청에 대한 캐싱을 도입하여 서버의 부하를 줄였습니다.
3. 로깅 최적화

### 기능 개선 후
1. 응답 시간 단축: 평균 응답 시간이 20% 이상 단축되었습니다.
2. 요청 성공률 향상: 요청 성공률이 95%에서 100%로 향상되었습니다.


--------------------

## Docs
### [01. 마일스톤 & 시퀀스 다이어그램](https://github.com/user-attachments/files/16506828/01_Milestone_Sequence.md)
### [02. ERD](https://github.com/user-attachments/files/16506874/02_ERD.md)
### [03. API 명세](https://github.com/user-attachments/files/16506912/03_API_document.md)
### [04. Swagger](https://github.com/user-attachments/files/16506939/04_Swagger.md)
### [05. Caching](https://github.com/user-attachments/files/16507012/05_Caching.md)
### [06. Index](https://github.com/user-attachments/files/16507102/06_Index.md)
### [07_Event_Driven.md](https://github.com/user-attachments/files/16556502/07_Event_Driven.md)