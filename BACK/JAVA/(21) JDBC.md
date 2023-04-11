# JDBC
- JDBC(Java Database Connectivity)는 자바 언어와 DBMS 서버간의 통신을 할 수 있게끔 개발하는데에 제시된 표준이고 기준인 인터페이스의 집합이다.
## 사전 준비
- JDBC가 포함하고 있는 인터페이스르 구현할 드라이버를 지정해야 하는데 아래와 같이 한다.
>```
> Class.forName("[드라이버 전체 경로]");
>```
>- 가령, MariaDB DBMS 서버에 접속하기 위한 드라이버 지정은 아래와 같다.
>```
> Class.forName("org.mariadb.jdbc.Driver");
>```
>- 단, `Class.forName`메서드는 `ClassNotFoundException`예외에 대한 시그니처가 있으므로 호출자는 이에 대비해야한다.
## Connection
- `Connection`(`java.sql.Connection`)은 자바 언어와 DBMS 서버간의 연결을 유지/관리하는 타입이다.
- `Connection`은 인터페이스로, 접속과 관련된 추상적인 부분을 직접 구현하는 것이 아닌 상기 `Class.forName`메서드를 통해 지정한 드라이버가 이를 구현하도록 해야하며, 객체는 `DriverManager`에서 아래와 같이 생성받는다.
>```
> Connection connection = DriverManager.getConnection("[URL]", "[사용자 이름]", "[비밀번호]");
>```
>- 가령, 호스트가 `localhost`, 포트가 `33063`, 사용자 이름이 `study`, 비밀번호가 `test1234`인 정보로 MariaDB DBMS에 접속하기 위해 아래와 같이 작성한다.
>```
> Connection connection = DriverManager.getConnection(
>   "jdbc:mariadb://localhost:33063",
>   "study",
>   "test1234");
>```
>- `Connection`객체는 `AutoCloseable`인터페이스를 구현함으로 Try-With-Resoruce 구문을 사용해야한다.
## PreparedStatement
- `PrepareStatement`(`java.sql.PreparedStatement`)는 DBMS에서 실행 쿼리를 유지/관리하는 인터페이스이다.
- `PreparedStatement`는 인터페이스로, 쿼리와 관련된 추상적인 부분을 직접 구현하는 것이 아닌 상기 `Connection`객체의`preparedStatement`메서드를 통해 구현하도록 해야하며, 아래와 같이 객체를 반환받는다.
>```
> PreparedStatement preparedStatement = connection.preparedStatement("[쿼리]");
>```
- `PreparedStatement` 객체는 `AutoCloseable` 인터페이스를 구현함으로 Try-With-Resource 구문을 사용해야 한다.
- 객체 메서드
  - `execute()`: 쿼리를 실행하여 해당 쿼리가 `ResultSet` 타입의 객체를 반환할 수 있는가의 여부를 반환한다.
  - `executeUpdate()`: 쿼리를 실행하여 영향을 받은 레코드의 개수를 반환한다. `CREATE`, `DROP`등 레코드와 관련되지 않은 쿼리의 경우 `-1`을 반환한다.
  - `executeQuery()`: 쿼리를 실행하여 실행 결과인 레코드(들)를 가진 `ResultSet` 객체를 반환한다.
## ResultSet
- `ResultSet`(`java.sql.ResultSet`)은 `SELECT`실행 결과를 담는 인터페이스이다.
- `PreparedStatement` 객체의 `executeQuery()`메서드를 실행하여 반환받을 수 있다.
- 단, 이는 인덱스(순번)를 활용한 값의 나열이 아니기 때문에 아래와 같이 반복(Iteration)절차를 거쳐 각 레코드에 접근할 수 있다.
>```
> ResultSet resultSet = preparedStatement.executeQuery();
> while (resultSet.next()) {
>   ...
> }
>```
- `ResultSet`객체는 `AutoCloseable`인터페이스를 구현함으로 Try-With-Resource 구문을 사용해야 한다.
- 객체 메서드
  - ★`getBoolean(String c)`: 가리키고 있는 레코드의 열 이름이 `c`인 논리형(`boolean`) 값을 반환한다.
  - `getByte(String c)`: 가리키고 있는 레코드의 열 이름이 `c`인 바이트(`byte`) 값을 반환한다.
  - ★`getDate(String c)`: 가리키고 있는 레코드의 열 이름이 `c`인 날짜/시간(`java.util.date`) 값을 반환한다.
  - ★`getDouble(String c)`: 가리키고 있는 레코드의 열 이름이 `c`인 실수(`double`) 값을 반환한다.
  - `getFloat(String c)`: 가리키고 있는 레코드의 열 이름이 `c`인 실수(`float`) 값을 반환한다.
  - ★`getInt(String c)`: 가리키고 있는 레코드의 열 이름이 `c`인 정수(`int`) 값을 반환한다.
  - ★`getLong(String c)`: 가리키고 있는 레코드의 열 이름이 `c`인 큰 정수(`long`) 값을 반환한다.
  - `getShort(String c)`: 가리키고 있는 레코드의 열 이름이 `c`인 작은 정수(`short`) 값을 반환한다.
  - ★`getString(String c)`: 가리키고 있는 레코드의 열 이름이 `c`인 문자열(`string`) 값을 반환한다.
  - `isFirst()`: 현재 가리키고 있는 레코드가 첫번째인지의 여부를 반환한다.
  - `isLast()`: 현재 가리키고 있는 레코드가 마지막인지의 여부를 반환한다.
  - ★`next()`: 다음으로 가리킬 레코드가 있는가의 여부를 반환하고 다음 레코드를 가리킨다.
  - `previous()`: 이전에 가리킬 레코드가 있는가의 여부를 반환하고 이전 레코드를 가리킨다.
  - `wasNull()`: 마지막으로 `get~`한 열의 값이 DB의 `NULL`이었는지의 여부를 반환한다.

## 활용
- `Connection`, `PreparedStatement`, `ResultSet` 객체를 활용하기 위해 아래와 같이 작성할 수 있다.
>- SELECT 쿼리가 아닌 경우
>```
> String url = "jdbc:mariadb://[호스트]:[포트]";
> String uid = "[사용자 이름]";
> String pwd = "[사용자 비밀번호]";
> Class.forName("[드라이버 전체 이름]");
> try (Connection connection = DriverManager.getConnection(url, uid, pwd))
>   String qurey = "[쿼리]";
>   try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
>     [실행 결과에 따른 구현부]
>   }
> } catch (Throwable throwable) {
>    [오류 발생시 구현부]
> }
>```
>- SELECT 쿼리를 실행하는 경우
>```
> String url = "jdbc:mariadb://[호스트]:[포트]";
> String uid = "[사용자 이름]";
> String pwd = "[사용자 비밀번호]";
> Class.forName("[드라이버 전체 이름]");
> try (Connection connection = DriverManager.getConnection(url, uid, pwd))
>   String qurey = "[SELECT 쿼리]";
>   try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
>     try (ResultSet resultSet = prepareStatement.executeQuery()) {
>       while (resultSet.next()) {
>         [레코드 반복 구현부]
>       }
>     }
>   }
> } catch (Throwable throwable) {
>    [오류 발생시 구현부]
> }
>```