# e-commerce

**Chapter 2 - 1주차**

### **`STEP 05`**

- 시나리오 선정  및 프로젝트 Milestone 제출
  - e-commerce
  - milestone
    ![Image](https://github.com/haeraeniii/e-commerce/assets/102571539/20db908f-ff3a-40c0-bc8c-d3f5e75687a8)
- 시나리오 요구사항 분석 자료 제출 (e.g. 시퀀스 다이어그램, 플로우 차트 등 )
  - 시퀀스 다이어그램
    - 상품을 주문한다.
      <img width="806" alt="상품을_주문한다" src="https://github.com/haeraeniii/e-commerce/assets/102571539/e5d32e95-b21e-4024-8827-d499341a561e">

### **`STEP 06`**

- ERD 설계 자료 제출



- API 명세 및 Mock API 작성
  ![Image](https://github.com/haeraeniii/e-commerce/assets/102571539/1ecf2137-c57f-4c5c-bc1f-66c799262acd)
    - api 명세서 주소 https://field-fish-a81.notion.site/e-commerce-API-c7bc2a4e12904ba1b03bae08b820f437

- Github Repo 제출 ( 기본 패키지 구조, 서버 Configuration 등 )


**Chapter 2 - 2주차**


### **`STEP 07`**

- API Swagger 작성
  https://app.swaggerhub.com/apis/haeryun0917/hhplus/1.0.0
- <img width="1440" alt="api 모음" src="https://github.com/haeraeniii/e-commerce/assets/102571539/4f04ad4c-fed6-4442-bb65-7d39eb7c922e">
  <img width="1432" alt=":api:products" src="https://github.com/haeraeniii/e-commerce/assets/102571539/d52e8174-b717-46b5-83ab-cbc9aa4e3e42">
  <img width="1436" alt=":api:products:{id}" src="https://github.com/haeraeniii/e-commerce/assets/102571539/c25267dc-4ca2-4854-b28c-e534ec8d0323">
  <img width="1434" alt=":api:products:productTop5" src="https://github.com/haeraeniii/e-commerce/assets/102571539/c3ab361f-3d90-4053-8f03-5ca5790ffc3e">
  <img width="1433" alt=":api:order:" src="https://github.com/haeraeniii/e-commerce/assets/102571539/0f1a16e4-002f-4745-958f-fab83720742e">
  <img width="1436" alt=":api:order:cancel" src="https://github.com/haeraeniii/e-commerce/assets/102571539/5695fe42-08a1-4cfa-b386-bd302bff1e30">
  <img width="1436" alt=":api:order:refundAll" src="https://github.com/haeraeniii/e-commerce/assets/102571539/13f6cb1a-6000-4a93-a6f4-1971db235c0d">
  <img width="1435" alt=":api:order:partialRefund" src="https://github.com/haeraeniii/e-commerce/assets/102571539/96aacc80-3ab4-48cb-a4e8-0d004b53975e">
  <img width="1436" alt=":api:order:orderList" src="https://github.com/haeraeniii/e-commerce/assets/102571539/2bbaf8ce-69d3-4566-9d53-1abcf50ce958">
  <img width="1436" alt=":api:customers:" src="https://github.com/haeraeniii/e-commerce/assets/102571539/bd1b045d-481f-40ed-b8c5-cbf515c40a0b">
  <img width="1438" alt=":api:customers:{id}" src="https://github.com/haeraeniii/e-commerce/assets/102571539/2b5682a2-f4b0-4d3f-944f-4125f1bca8cf">
  <img width="1435" alt=":api:customers:charge" src="https://github.com/haeraeniii/e-commerce/assets/102571539/14a866db-d7f4-42a6-830f-97478950cb02">
  <img width="1434" alt=":api:customers:checkBalance" src="https://github.com/haeraeniii/e-commerce/assets/102571539/9a5369e4-60ea-4f35-9a60-fbde816332c1">
  <img width="1436" alt=":api:cart:" src="https://github.com/haeraeniii/e-commerce/assets/102571539/1d221f94-ea11-461c-9853-e994bc27dbc6">
  <img width="1439" alt=":api:cart:add" src="https://github.com/haeraeniii/e-commerce/assets/102571539/af8b4088-9690-4e97-a9ca-d3bf1ba2e401">
  <img width="1434" alt=":api:cart:delete:{id}" src="https://github.com/haeraeniii/e-commerce/assets/102571539/8a10c5bd-6ae9-4ea5-82fc-4f08c7b3c84e">
  <img width="1439" alt=":api:cart:deleteAll:{customerId}" src="https://github.com/haeraeniii/e-commerce/assets/102571539/dea42bef-615e-4ac2-85ac-802c557b37b0">
  
- 주요 비즈니스 로직 개발 및 단위 테스트 작성
  
- 제출 : API Swagger 캡처

### **`STEP 08`**

- 비즈니스 Usecase 개발
- Usecase 별 통합 테스트 작성

> > API 의 완성이 목표가 아닌, 기본 및 주요 기능의 비즈니스 로직 및 유즈케이스는 구현이 완료 되어야 함. ( `Business Layer` )
> >
>
> > DB Index , 대용량 처리를 위한 개선 포인트 등은 추후 챕터에서 진행하므로 목표는 `기능 개발의 완료` 로 합니다. 최적화 작업 등을 고려하는 것 보다 모든 기능을 정상적으로 제공할 수 있도록 해주세요.
> 특정 기능을 왜 이렇게 개발하였는지 합당한 이유와 함께 기능 개발을 진행해주시면 됩니다.
> >