# Index

------------

## 비교해야 할 값 알아보기
1. **NDV(Number of Distinct Value)**<br/>
   특정 컬럼에 Unique한 값이 얼마나 있는가.<br/>
   ex) order라는 테이블 안에 customer_id가 4개 있으므로 NDV는 4가 된다.<br/>
   <br/>
2. **선택도(Selectivity)**<br/>
   전체 레코드 중 조건절에 의해 선택될 것으로 예상되는 레코드의 비율(%).<br/>
   > 선택도 = 카디널리티 / 총 레코드 수<br/>

   <ins>**선택도가 낮을수록 인덱스의 후보가 되기 좋다.**<ins><br/>
   <br/>
3. **카디널리티**<br/>
   특정 데이터 집합의 Unique한 값의 개수.
   > 카디널리티 = 선택도 * 총 레코드 수

   <ins>**카디널리티가 높을수록 검색 속도가 빨라진다.**<ins><br/>
   <br/>
#### "카디널리티가 높을수록, 선택도가 낮을수록 중복도가 낮은 컬럼이다."

-----------

## 인덱스 설정할 테이블

**1. ORDER**

|             |                  선택도(현재 데이터)                  |      카디널리티(현재 데이터)       |
|:------------|:---------------------------------------------:|:------------------------:|
| customer_id |             고객 수(4) / 총 주문량(110)              |         고객 수(4)          |
| created_at  | 특정 날짜 혹은 특정 날짜 범위(3days)  / 회원가입일로부터 현재까지의 날짜 | 특정 날짜 혹은 특정 날짜 범위(3days) |

1. 주문 내역
   - 카디널리티가 4 <br/>
     (고객 한명당 옷을 여러개 주문하기 때문에 카디널리티가 높아도 평균적으로 선택도가 낮을 확률이 매우 높음)
   - 선택도가 낮기 때문에 인덱스를 만드는 것이 효율적이라고 판단.<br/>
     (주문 내역의 경우 읽기보다는 쓰기가 더 많이 이루어지지만 중간에 데이터가 삭제되거나 들어가서 정렬을 다시하는 경우는 없다고 생각)<br/>
   <br/>
2. 주문 날짜별 내역
   - 카디널리티가 3
   - 선택도가 3/가입한지 오래될수록 많은 날짜 (새로 유입되는 회원보다 기존 회원이 더 많을 것으로 판단해서 선택도가 낮음)
   - 복합 인덱스를 이용해 특정 고객이 특정 날짜에 주문한 내역을 빠르게 확인할 수 있음


**2. ORDERITEM**

|                |                  선택도(현재 데이터)                   |      카디널리티(현재 데이터)       |
|:---------------|:----------------------------------------------:|:------------------------:|
| ordered_at     | 특정 날짜 혹은 특정 날짜 범위(3days)  / 회원가입일로부터 현재까지의 날짜  | 특정 날짜 혹은 특정 날짜 범위(3days) |
| product_id     |             상품 갯수(5)/총 주문 수량(114)              |         상품 갯수(5)         |


1. 인기상품 조회<br/>
    - 날짜 우선 체크 후 상품 아이디를 찾도록 하여 검색에 속도를 높임.<br/>
    - 날짜만 검색하는 단일 인덱스보다는 복합 인덱스로 검색하는 것이 더 빠른 결과를 냄.<br/>
    - 인기 상품 조회의 경우 정렬이 필요하지 않고 테이블 여러개를 조인해서 데이터를 가져와야 하기 때문에 인덱스를 적용하는데 가장 적합한 테이블이라고 판단.
    <br/>

---------

## 적용 결과

### 주문 내역 조회

|          |    인덱스 적용 전   |인덱스 적용 후 |
|:---------|:-------------:|:-------:|
| 소요 시간 |     167ms     |  136ms  |

#### EXPLAIN

복합 인덱스가 있기 때문에 주문 내역에 대한 단일 인덱스는 만들 필요가 없다고 판단.<br/>
**scanCount가 많은 이유는 테스트용으로 한 사람이 3일 안에 주문을 100개 이상 했기 때문.<br/> 
(보통은 scanCount가 높으면 성능 저하가 발생할 수 있음을 고려)<br/>

```
EXPLAIN ANALYZE SELECT * FROM "ORDER" WHERE customer_id=1;

PLAN  
SELECT
    "PUBLIC"."ORDER"."CREATED_AT",
    "PUBLIC"."ORDER"."CUSTOMER_ID",
    "PUBLIC"."ORDER"."ORDER_ID"
FROM "PUBLIC"."ORDER"
    /* PUBLIC.IDX_ORDER_CUSTOMER_CREATED: CUSTOMER_ID = CAST(1 AS BIGINT) */
    /* scanCount: 108 */
WHERE "CUSTOMER_ID" = CAST(1 AS BIGINT)
```
### 날짜별 주문 내역 조회
   > CREATE INDEX idx_order_created_at ON `order` (created_at);

|             |    인덱스 적용 전   |인덱스 적용 후 |
|:------------|:-------------:|:-------:|
| 소요 시간 |     167ms     |  136ms  |

#### EXPLAIN
    
단일 인덱스 IDX_ORDER_CREATED_AT 사용<br/>
날짜별 조회는 인덱스가 있으면 효율적이라고 생각.

```
EXPLAIN ANALYZE SELECT * FROM "ORDER" WHERE created_at  BETWEEN '2024-08-05' AND '2024-08-08';

PLAN  
SELECT
    "PUBLIC"."ORDER"."CREATED_AT",
    "PUBLIC"."ORDER"."CUSTOMER_ID",
    "PUBLIC"."ORDER"."ORDER_ID"
FROM "PUBLIC"."ORDER"
    /* PUBLIC.IDX_ORDER_CREATED_AT: CREATED_AT >= '2024-08-05'
        AND CREATED_AT <= '2024-08-08'
     */
    /* scanCount: 110 */
WHERE "CREATED_AT" BETWEEN '2024-08-05' AND '2024-08-08'

```
    
### 특정 고객의 특정 기간 동안의 주문 내역 조회 - 복합 인덱스 생성
   > CREATE INDEX idx_order_created_customer ON `order` (created_at, customer_id);


|          |    인덱스 적용 전   |인덱스 적용 후 |
|:---------|:-------------:|:-------:|
| 소요 시간 |     128ms     |  94ms  |

#### EXPLAIN

```
EXPLAIN ANALYZE SELECT * FROM "ORDER" WHERE customer_id=1 AND created_at  BETWEEN '2024-08-05' AND '2024-08-08';

PLAN  
SELECT
    "PUBLIC"."ORDER"."CREATED_AT",
    "PUBLIC"."ORDER"."CUSTOMER_ID",
    "PUBLIC"."ORDER"."ORDER_ID"
FROM "PUBLIC"."ORDER"
    /* PUBLIC.IDX_ORDER_CREATED_CUSTOMER: CUSTOMER_ID = CAST(1 AS BIGINT)
        AND CREATED_AT >= '2024-08-05'
        AND CREATED_AT <= '2024-08-08'
     */
    /* scanCount: 107 */
WHERE ("CUSTOMER_ID" = CAST(1 AS BIGINT))
    AND ("CREATED_AT" BETWEEN '2024-08-05' AND '2024-08-08')

```

### 인기상품 top5 조회

 **<ins>"복합인덱스가 훨씬 효율적으로 검색 가능"<ins>**

### 비교 
   1. OrderItem 테이블에 복합 인덱스 생성
   > CREATE INDEX idx_orderItem_ordered_at_product_id ON orderItem (ordered_at, product_id);
    
#### EXPLAIN

```
EXPLAIN ANALYZE SELECT product_id, COUNT(*) AS order_count
FROM orderItem
WHERE ordered_at BETWEEN '2024-08-05' AND '2024-08-08'
GROUP BY product_id
ORDER BY order_count DESC
LIMIT 5;

PLAN  
SELECT
    "PRODUCT_ID",
    COUNT(*) AS "ORDER_COUNT"
FROM "PUBLIC"."ORDERITEM"
    /* PUBLIC.IDX_ORDERITEM_ORDERED_AT_PRODUCT_ID: ORDERED_AT >= '2024-08-05'
        AND ORDERED_AT <= '2024-08-08'
     */
    /* scanCount: 114 */
WHERE "ORDERED_AT" BETWEEN '2024-08-05' AND '2024-08-08'
GROUP BY "PRODUCT_ID"
ORDER BY 2 DESC
FETCH FIRST 5 ROWS ONLY

```

   2. OrderItem 테이블에 단일 인덱스 생성
   > CREATE INDEX idx_orderItem_ordered_at ON orderItem (ordered_at);

#### EXPLAIN

```
EXPLAIN analyze SELECT * FROM ORDERITEM WHERE ordered_at BETWEEN '2024-08-05' AND '2024-08-08';

PLAN  
SELECT
    "PUBLIC"."ORDERITEM"."ID",
    "PUBLIC"."ORDERITEM"."ORDER_ID",
    "PUBLIC"."ORDERITEM"."ORDER_QUANTITY",
    "PUBLIC"."ORDERITEM"."ORDERED_AT",
    "PUBLIC"."ORDERITEM"."PRICE",
    "PUBLIC"."ORDERITEM"."PRODUCT_ID",
    "PUBLIC"."ORDERITEM"."PRODUCT_OPTION_ID",
    "PUBLIC"."ORDERITEM"."COLOR",
    "PUBLIC"."ORDERITEM"."PRODUCT_NAME",
    "PUBLIC"."ORDERITEM"."SIZE"
FROM "PUBLIC"."ORDERITEM"
    /* PUBLIC.IDX_ORDERITEM_ORDERED_AT: ORDERED_AT >= '2024-08-05'
        AND ORDERED_AT <= '2024-08-08'
     */
    /* scanCount: 114 */
WHERE "ORDERED_AT" BETWEEN '2024-08-05' AND '2024-08-08'

```


|      | 인덱스 적용 전 | 인덱스 적용 후(복합 인덱스) | 인덱스 적용 후(단일 인덱스)  |
|:-----|:--------:|:----------------:|:-----------------:|
| 소요 시간 |   58ms   |       55ms       |       58ms        |