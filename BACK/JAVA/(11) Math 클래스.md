# Math 클래스
- `Math` (`java.lang.Math`) 클래스는 **수학과 관련된 편의 기능**을 제공한다.
## 정적 멤버
- `E`: `e`(자연상수)이다.
- `PI`: `pi`(원주율)이다.
## 정적 메서드
- ★`abs(int|long|float|byte x)`: `x`의 절댓값을 반환한다.
- ★`addExact(int|long a, int|long b)`: `a`와 `b`의 합을 반환한다. 단,오버플로우가 발생할 경우 예외(`java.lang.ArithmeticException`)를 발생시킨다.
- `cbrt(double x)`: `x`의 세제곱근을 반환한다.
- ★`ceil(double x)`: `x`를 올림하여 반환한다.
- `decrementExact(int|long x)`: `x`에 `1`을 뺀 값을 반환한다. 단,오버플로우(언더플로우)가 발생할 경우 예외(`java.lang.ArithmeticException`)를 발생시킨다.
- ★`floor(double x)`: `x`를 내림하여 반환한다.
- `incrementExact(int|long x)`: `x`에 `1`을 더한값을 반환한다. 단,오버플로우(언더플로우)가 발생할 경우 예외(`java.lang.ArithmeticException`)를 발생시킨다.
- `log(double x)`: 자연로그`x`를 반환한다.
- `log10(double x)`: 상용로그 `x`를 반환한다.
- ★`max(int|long|float|double a, int|long|float|double b)`: `a`와 `b`중 더 큰수를 반환한다.
- ★`min(int|long|float|double a, int|long|float|double b)`: `a`와 `b`중 더 작은수를 반환한다.
- `multiplyExact(int|long a, int|long b)`: `a`와 `b`의 곱을 반환한다. 단,오버플로우(언더플로우)가 발생할 경우 예외(`java.lang.ArithmeticException`)를 발생시킨다.
- `negateExact(int|long x)`: `x`를 부정하여 반환한다. 단,오버플로우(언더플로우)가 발생할 경우 예외(`java.lang.ArithmeticException`)를 발생시킨다.
- `pow(double a, double b)`: `a`의 `b`승을 반환한다.
- `random()`: `0`이상 `1`미만의 랜덤한 실수를 반환한다.
- ★`sqrt(double x)`: `x`의 제곱근을 반환한다.
- `subtractExact(int|long a, int|long b)`: `a`에서 `b`를 뺀 값을 반환한다. 단,오버플로우(언더플로우)가 발생할 경우 예외(`java.lang.ArithmeticException`)를 발생시킨다.
- `toIntExact(long x)`: 큰 정수(`long`)인 `x`를 일반정수(`int`)로 변환하여 반환한다. 단,오버플로우(언더플로우)가 발생할 경우 예외(`java.lang.ArithmeticException`)를 발생시킨다.
- 생략된 것: `acos`, `asin`, `atan`, `atan2`, `cos`, `cosh`, `exp`, `expm1`, `floorDiv`, `floorMod`, `fma`
## 객체 메서드