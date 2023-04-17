# MyBatis

- 자바에서 사용가능한 PF(지속성 프레임워크, Persistence Framework)중 하나로, 전신으로 iBatis를 두고 있다.
- 기존의 DAO(Data Access Object)대신, 관리되는 연결(Managed Connections) 및 구현부 자동완성 등을 통해 성능 및 개발 용이성을 향상시킨다.
    - 기본적으로 Spring Boot Starter JDBC 의존성은 연결 관리를 위한 커넥션 풀(Connection Pool)로 히카리 CP(Hikari Connection Pool)를 사용한다.

## 준비하기

1. 프로젝트 의존성 준비하기
    - 스프링 부트에서 MyBatis를 활용하기 위해 아래와 같은 의존성이 요구된다.(단, MariaDB 연결을 예로 사용)
    - `spring-boot-starter-jdbc` (`org.springframework.boot`)
    - `mybatis-spring-boot-starter` (`org.mybatis.spring.boot`)
    - `mariadb-java-client` (`org.mariadb.jdbc`)
2. 데이터베이스 연결 정보 설정하기
    - 스프링 부트가 DBMS에 접속하기 위한 정보를`application.properties`에 지정한다.
    - `spring.datasource.driver`
    - `spring.datasource.url`
    - `spring.datasource.username`
    - `spring.datasource.password`
3. XML 경로 설정하기
    - 스프링부트에서 인식할 MyBatis Mapper XML 파일의 경로를 아래와 같이 `application.properties`에 지정한다.
    - `mybatis.mapper-locations=classpath:mappers/**/*.xml`
4. XML 프리셋 준비하기
    - 쿼리를 포함할 MyBatis Mapper XML 파일을 아래 구조에 맞게 작성한다.
   > ```
    > <?xml version="1.0" encoding="UTF-8" ?>
    > <!DOCTYPE mapper
    >    PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
    >    "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
    > <mapper namespace="[연결할 인터페이스 경로]">
    > </mapper>
   >```
   >- `<mapper>`태그의 `namespace`속성 값은 이 XML 파일이 연결된 Mapper 인터페이스의 Java 경로이며 자동완성을 이용하고, 완성 후 반드시 (컨트롤 + 클릭 등을 통해) 이동되는지
      확인한다.
   >- 위 방식으로 작성한 Mapper XML 파일은 `<3>`에서 정한 `mybatis.mapper-locations`의 속성 값에 맞는 위치에 저장되어야 한다.

## 태그

- `<insert>`: `INSERT` 쿼리를 실행하기 위해 사용한다.
  - `id`:(필수)XML과 연결된 인터페이스의 연결될 메서드의 이름을 지정한다.
  - `keyColumn`: (선택) `INSERT`시 `AUTO_INCREMENT`등에 의해 자동으로 생성된 값을 가져올 테이블상의 열이름을 지정한다. `useGeneratedKeys`속성 값이 `true`일 때만 유효하다.
  - `keyProperty`: (선택) `INSERT`시 `AUTO_INCREMENT`등에 의해 자동으로 생성된 값을 지정할 `Entity`등의 **필드이름**(멤버 변수 이름)을 지정한다. `useGeneratedKeys`속성 값이 `true`일 때만 유효하다.
  - `useGeneratedKeys`: (선택) `INSERT` 시 `AUTO_INCREMENT` 등에 의해 자동으로 생성된 값을 가지고 올지의 여부를 지정한다. 기본 값은 `false`이고 , `true`혹은 `false`로 지정해야 한다.
  - `parameterType`: (선택)연결된 메서드가 매개변수를 가진다면, 이의 타입을 지정한다.
- `<select>`:
- `<update>`:
- `<delete>`: