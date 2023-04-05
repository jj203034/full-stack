# Long 클래스
- `Long`(`java.lang.Long`)클래스는 큰 정수(`long`)와 관련된 편의 기능을 제공하고, 해당 타입 자체가(`long`처럼)큰 정수 리터럴로 작동할 수 있다.
- `Long`은 참조타입이다.(`null`허용)
## 정적 멤버 변수
- ★`MAX_VALUE`: `long`타입이 가질 수 있는 최대값이다.
- ★`MIN_VALUE`: `long`타입이 가질 수 있는 최소값이다.
## 정적 메서드
- ★`parseLong(String s)`: 문자열 `s`를 큰 정수로 변환하여 반환한다.
- `parseLong(String s, int radix)`: `radix`진수인 문자열 `s`를 큰 정수로 변환하여 반환한다.
- `toBinaryString(long i)`: 큰 정수 `l`를 이진수인 문자열로 반환한다.
- `toHexString(long i)`: 큰 정수 `l`를 16진수인 문자열로 반환한다.
- `toOctalString(long i)`: 큰 정수 `l`를 8진수인 문자열로 반환한다.
- `toString(long i)`: 큰 정수 `l`를 문자열로 반환한다.
## 객체 메서드
- `byteValue()`: 객체가 가진 큰 정수값을 `byte` 타입으로 변환한다.(`long > byte` 기본타입 형변환)
- `doubleValue()`: 객체가 가진 큰 정수값을 `byte` 타입으로 변환한다.
- `equals(Object o)`: 객체가 전달된 `o`와 같은가의 여부를 반환한다.
- `floatValue()`: 객체가 가진 큰 정수값을 `float` 타입으로 변환한다.
- `intValue()`: 객체가 가진 큰 정수값을 `int` 타입으로 변환한다.
- `longValue()`: 객체가 가진 큰 정수값을 `long` 타입으로 변환한다.
- `shortValue()`: 객체가 가진 큰 정수값을 `short` 타입으로 변환한다.(`long > short`강제 형변환)
- `toString()`: 객체가 가진 큰 정수값을 문자열로 변환한다.