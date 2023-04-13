# MVC
- **MVC**는 Model-View-Controller의 줄임말로 웹 개발 및 프로그램 개발시에 사용하는 일종의 패턴이다.
- 원활한 협업 및 향후의 유지관리, 보수를 위해 MVC 패턴을 지키면서 개발을 하는것이 중요하다.

![(2) MVC.png](%282%29%20MVC.png)
## Model

### Service

### Mapper(DAO, Data Access Object)

## View

- View는 사용자에게 보여질 화면을 구현하는 부분이다. 주로 동적인 HTML 표시를 위해 많이 사용한다.
- 스프링 부트에서 View를 처리하기 위한 엔진의 종류가 많은데 대표적인 예는 아래와 같다.
    - JSP
    - Thymeleaf
    - Groovy
    - FreeMaker

## Controller
- MVC 패턴에서 실질적으로 사용자의 요청을 받아 응답을 돌려주는 역할을 한다.
- 요청을 받을 수 있는 주소(경로)에 대한 맵핑(Mapping) 메서드를 가지고 있다.