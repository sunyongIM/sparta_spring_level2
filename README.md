# 필수과제

## 프레임워크와 라이브러리의 차이점

### 라이브러리

- 단순 활용가능한 **도구**들의 집합

> 특정한 부분 기능만을 수행하도록 제작되었고,
> 사용자 라이브러리의 기능을 직접 호출하는 프로그램을 실행하거나,
> API를 개발해야 사용할 수 있다.
>
> ex) JS - jQuery, Bootstrap 등



### 프레임워크

- 개발의 다양한 기능들을 통합한 공통적인 **개발환경**을 제공해주는 것

> 개발 시 프레임워크의 개발환경 규칙에 맞게 구현해야 한다 (프레임워크를 수정하여 사용할 수도 있다)
>
> ex) JS - React, Vue, Angular	/	 Java - Spring	/	Python - Django



### 비교

라이브러리 - 단순한 클래스의 집합체

프레임워크 - 여러기능을 가진 클래스와 **라이브러리가 합쳐진 상태**

> ex) 장난감 로봇을 만들 때
> 레고의 블럭을 사용하여 조립하여 만드는 것 <= 라이브러리 사용
> 변신합체로봇 합체하기 <= (로봇 만들기) 프레임워크



<img src="md-images/%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC_%EC%98%88%EC%8B%9C.gif" alt="라이브러리_예시" style="zoom: 67%;" />

레고로 로봇을 만드는 것과 같이 라이브러리는 **특정한 부분 기능만을 수행하도록 제작된** (로봇용으로만 제작한 부품이 아님) 프로그램을 개발자가 **직접 사용**하여 (조립) 개발하는 방식
`그만큼 라이브러리의 독창적이고 다양한 활용이 나올 수 있지 않을까 싶다 (고인물 전용)`
`프레임워크도 누군가가 라이브러리를 사용하여 만든 것`



<img src="md-images/%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC_%EC%98%88%EC%8B%9C.gif" alt="프레임워크_예시" style="zoom: 67%;" />

프레임워크는 변신합체로봇과 같이 다양한 기능들을 통합한 **공통적인 개발환경** (로봇들은 물리적으로 조립한다. 빨강이는 가슴으로 쓰이고, 노랑이는 다리로 쓰인다 등의 규칙)을 제공해 준다.
`결과물이 비슷하고, 작업 속도가 빨라지며, 필요 숙련도가 낮아진다 (목표 달성 측면에서 효율이 좋다)`



## 코드를 구현할때 예외처리를 위해 무엇을 했나요?

다양한 클라이언트의 관점에서 예외가 나올 상황을 예상하고, 예외처리의 방법과 예외처리된 결과물을 어떻게 다룰 것 인지에 대한 이야기를 프론트와 나누었다.



# 백엔드 공통

## Restful이란?

Rest [ Representational State Transfer ] 즉, HTTP Method (POST, GET, PUT, DELETE)를 이용하여 웹의 모든 자원에 고유한 ID인 HTTP URI를 부여하는 방법으로 통신하는 것
HTTP와 URI 기반으로 자원에 접근할 수 있도록 제공하는 애플리케이션 개발 인터페이스이다. 기본적으로 개발자는 HTTP 메소드와 URI 만으로 인터넷에 자료를 `CRUD` 할 수 있다.

REST의 구성

- 자원(Resource) - URL
- 행위(Verb) - Http Method
- 표현(Representations)

'REST API'를 제공하는 웹 서비스를 **'RESTful'** 하다고 할 수 있다

## 왜 Restful하게 짜야하나요?

RESTful APIs 개발하는 가장 큰 이유는 **Client Side를 정형화된 플랫폼이 아닌 모바일, PC, 어플리케이션 등 플랫폼에 제약을 두지 않는 것을 목표로 했기 때문**이다

스마트 기기들이 등장하면서 TV, 스마트 폰, 테블릿 등 Client 프로그램이 다양화 되고 그에 맞춰 Server를 일일이 만든다는 것이 비효율적이기 때문에, 개발자들이 Client Side를 전혀 고려하지 않고 메시지 기반, XML, JSON과 같은 **Client에서 바로 객체로 치환 가능한 형태의 데이터 통신을 지향하게 되면서 Server와 Client의 역할을 분리하게 되었다**

## Restful의 장/단점

### 장점

1. HTTP 프로토콜의 인프라를 그대로 사용하며, HTTP 프로토콜의 장점을 함께 사용할 수 있다
2. Hypermedia API의 기본을 충실히 지키면서 범용성을 보장한다
3. REST API 메시지로 의도를 명확하게 파악할 수 있다
4. 서버와 클라이언트의 역할을 명확하게 분리한다

### 단점

1. REST는 point-to-point 통신모델을 기본으로 한다. 따라서 서버와 클라이언트가 연결을 맺고 상호작용해야하는 어플리케이션의 개발에는 적당하지 않다.
2. REST는 URI, HTTP 이용한 아키텍처링 방법에 대한 내용만을 담고 있다. 보안과 통신규약 정책 같은 것은 전혀다루지 않는다. 따라서 개발자는 통신과 정책에 대한 설계와 구현을 도맡아서 진행해야 한다.
3. HTTP에 상당히 의존적이다. REST는 설계 원리이기 때문에 HTTP와는 상관없이 다른 프로토콜에서도 구현할 수 있기는 하지만 자연스러운 개발이 힘들다. 다만 REST를 사용하는 이유가 대부분의 서비스가 웹으로 통합되는 상황이기에 큰 단점이 아니게 되었다.
4. CRUD 4가지 메소드만 제공한다. 대부분의 일들을 처리할 수 있지만, 4가지 메소드 만으로 처리하기엔 모호한 표현이 있다.

## Restful의 대안은?

### GraphQL

> 특징 : **Client에서 Query를 통해 Server에 데이터를 요청 할 수 있다**

- 효율적인 데이터 로딩 - 필요한 최소한의 정보만 별도 요청이 가능하다

- 프론트엔드의 다양한 프레임워크와 플랫폼에 적절하다

## Restful하게 짜기 위해 무엇을 고려했나요?

REST의 세부 규칙을 지켜 URI를 생성하였음

**1. 슬래시 구분자 ( / )는 계층 관계를 나타내는데 사용한다.**

**2. URI 마지막 문자로 슬래시 ( / )를 포함하지 않는다.**

- 즉 URI에 포함되는 모든 글자는 리소스의 유일한 식별자로 사용되어야 하며 URI가 다르다는 것은 리소스가 다르다는 것
- 역으로 리소스가 다르면 URI도 달라져야 한다.

**3. 하이픈 ( - )은 URI 가독성을 높이는데 사용한다.**

**4. 밑줄 ( _ )은 URI에 사용하지 않는다.**

**5. URI 경로에는 소문자가 적합하다.**

- URI 경로에 대문자 사용은 피하도록 한다.

**6. 파일확장자는 URI에 포함하지 않는다.**

- REST API 에서는 메시지 바디 내용의 포맷을 나타내기 위한 파일 확장자를 URI 안에 포함시키지 않는다.
- 대신 Accept Header 를 사용한다.
- ex) `GET`: `http://restapi.exam.com/orders/2/Accept: image/jpg`

**7. 리소스 간에 연관 관계가 있는 경우**

- /리소스명/리소스ID/관계가 있는 다른 리소스 명
- ex) GET: /users/2/orders (일반적으로 소유의 관계를 표현할 때 사용)

# Spring

## Entity 설계를 위해 무엇을 하였나요? 연관관계에 근거하여 설명해주세요.

실제 사용하는 데이터에 맞게 연관관계를 형성하였고,
(좋아요 기능) ManyToMany관계가 형성되지 않도록 테이블을 분리하였다.



## N+1 문제와 해결법

1번의 쿼리를 의도했는데 N개의 쿼리가 더 생기는 것 (주로 다대일 연관관계의 엔티티를 불러올 때 발생)

하위 엔티티들을 첫 쿼리 실행시 한번에 가져오지 않고, Lazy Loading으로 필요한 곳에서 사용되어 쿼리가 실행될때 발생하는 문제가 N+1 쿼리 문제이다

해결법

Join Fetch 사용 <= Inner Join

@EntityGraph 사용 <= Outter Join

> LinkedHashSet이나 distinct를 사용하여 



## 레이지 로딩, 이거 로딩의 원리





## 과제 구현사항

- 회원 가입 페이지
  - 회원 가입을 구현할 때 인증 인가 방식은 자유롭게 선택해주시면 됩니다.
  - 닉네임은 `최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성하기
  - 비밀번호는 `최소 4자 이상이며, 닉네임과 같은 값이 포함된 경우 회원가입에 실패`로 만들기
  - 비밀번호 확인은 비밀번호와 정확하게 일치하기
- 로그인 페이지
  - 로그인 버튼을 누른 경우 닉네임과 비밀번호가 데이터베이스에 등록됐는지 확인한 뒤, 하나라도 맞지 않는 정보가 있다면 "닉네임 또는 패스워드를 확인해주세요"라는 메세지를 프론트엔드에서 띄워줄 수 있도록 예외처리 하기
- 로그인 검사
  - 로그인 하지 않은 사용자도, 게시글 목록 조회는 가능하도록 하기
  - 로그인하지 않은 사용자가 좋아요 버튼을 눌렀을 경우, "로그인이 필요합니다." 라는 메세지를 프론트엔드에서 띄워줄 수 있도록 예외처리 하기
  - 로그인 한 사용자가 로그인 페이지 또는 회원가입 페이지에 접속한 경우 "이미 로그인이 되어있습니다."라는 메세지로 예외처리하기
  - 인증 인가를 어떤 개념(Token/Session)을 채택 했는지, 그 이유에 대해서 설명하기
- CORS 해결하기
  - CORS란 무엇이며, 어떤 상황에서 일어나는지 / 어떻게 해결하는지 알아보고, 프로젝트에 적용하기
- 좋아요 순 정렬
  - 정렬 기준 중 하나를 선택해주세요!
    - 생성일 순
    - 좋아요 순
    - view 순
