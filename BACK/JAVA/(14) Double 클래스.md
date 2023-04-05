# Double 클래스
- `Double`(`java.lang.Double`)클래스는 실수와 관련된 편의 기능을 제공하고, 해당 타입 자체가(`double`처럼)실수 리터럴로 작동할 수 있다.
- `Double`은 참조타입이다.(`null`허용)
## 정적 멤버 변수
- ★`MAX_VALUE`: `double`타입이 가질 수 있는 최대값이다.
- ★`MIN_VALUE`: `double`타입이 가질 수 있는 최소값이다.
- `NaN`: 숫자가 아닌(Not a Number)값.
- `NEGATIVE_INFINITY`: 음의 무한수.
- `POSITIVE_INFINITY`: 양의 무한수.
## 정적 메서드
- `isFinite(double d)`: 실수 `d`가 유한한가의 여부를 반환한다.
- `isInfinite(double d)`: 실수 `d`가 무한한가의 여부를 반환한다.
- `isNaN(double d)`: 실수 `d`가 `NaN`인가의 여부를 반환한다.
- `parseDouble(String s)`: 문자열 `s`를 실수로 변환하여 반환한다
- `toString(double d)`: 실수 `d`를 문자열로 반환한다.
## 객체 메서드
- `byteValue()`: 객체가 가진 실수값을 `byte` 타입으로 변환한다.(`double > byte` 강제 형변환)
- `doubleValue()`: 객체가 가진 실수값을 `byte` 타입으로 변환한다.
- `equals(Object o)`: 객체가 전달된 `o`와 같은가의 여부를 반환한다.
- `floatValue()`: 객체가 가진 실수값을 `float` 타입으로 변환한다.(`double > float`강제 형변환)
- `intValue()`: 객체가 가진 실수값을 `int` 타입으로 변환한다.(`double > int`강제 형변환)
- `isInfinite()`: 객체가 가진 실수값이 무한한가의 여부를 반환한다.
- `isNaN`: 객체가 가진 실수값이 `NaN`인가의 여부를 반환한다.
- `longValue()`: 객체가 가진 실수값을 `long` 타입으로 변환한다.(`double > long`강제 형변환)
- `shortValue()`: 객체가 가진 실수값을 `short` 타입으로 변환한다.(`double > short`강제 형변환)
- `toString()`: 객체가 가진 실수값을 문자열로 변환한다.