# Caching

> - 캐시란?<br/>
> 복잡한 연산이나 시간이 오래 걸리는 연산을 미리 수행/저장해서 빨리 가져와서 쓸 수 있게 하는 것
>
>
> - 어떻게 활용될 수 있을까?<br/>
> DB가 버틸 수 있는 최대 요청수보다 많은 작업이 요청되면 느려질 수 있다. 이 때 캐시를 사용하면 DB부하를 줄일 수 있고 빠르게 응답 가능하다.
>
>
> - 캐시를 사용하기에 적절한 데이터를 판단하는 기준?
  >   1. 데이터가 변경에 민감한가?
>   2. 연산이 비싼가?
>   3. 데이터 변경이 전파가 되나?

### EHcache vs redis
- EHcache
    1. 로컬캐시로써 특정 서버에 종속된다.
    2. 그러므로 서버 환경에서는 데이터 싱크가 필요하다.
    3. 동기화가 속도에 영향을 줄 수 있고 데이터 유실 가능성이 있다.
    4. 데이터 접근이 빠르다.
- redis
    1. 인메모리 데이터 구조 저장소로, 높은 성능과 확장성을 제공한다.
    2. 독립적인 서버로 구축되어 네트워크 통신으로 서버들이 데이터에 접근한다.
    3. 속도는 로컬보다 느리다.
    4. 데이터 동기화 이슈가 없다.

### e-commerce에서 chching 해야할 데이터는 무엇일까?
- 빈번하게 발생하는데 속도이슈가 생길만한 것, 실시간 보장이 필요없는 것
    1. 인기 상품 및 상품 데이터 조회
    2. 주문 완료된 order & orderItem
    3. 결제 완료된 payment

- Cache의 Termination Type
    - 상품정보 조회, 상품 디테일 정보 조회 : Eviction
      상품 정보가 수정될 때 기존캐시를 날리는 방법
    - 인기상품 조회 : Expiration
      시간을 정해두고 주기적으로 삭제하는 방법

### 캐시 사용으로 인한 다양한 이슈
- Look-Aside caching(값이 없을 때 원본 데이터를 DB와 같은 곳에서 읽어와서 채우는 것)으로 인한 race condition 발생 가능.
- thundering herd 이슈 -> expire time보다 조금 더 빨리 재계산 해줘서 해당 값을 남아있게 한다. 또는 캐시 락킹 방법 사용 가능
- 캐시 서버들 간에 데이터가 고루 분배되지 않아 특정 서버에만 캐시가 많이 쌓이는 이슈 -> adaptive consistency 개념 적용 가능
- 메모리는 비싸다는 단점 -> SDS와 같은 좋은 장비에 cold 데이터를 보관하고 hot한 데이터만 메모리에 두는 방법으로도 해결 가능