# Throwable
- `Throwable`(`java.lang.Throwable`)클래스는 **던질 수 있는**이라는 의미로 자바 내에서 발생시킬수 있는 오류를 의미한다.
## Error
- `Error`(`java.lang.Error`)클래스는 `Throwable`을 상속받아 자바 실행중 발생할 수 있는 (시스템,언어적인)오류를 구현한다.
- 사용자가 직접 `Error`클래스를 상속받아 새로운 `Error`를 만들거나,고의로 발생시키는 것은 **권장되지 않는다.**
- *Unchecked Throwable*로, 별도의 명시 없이 발생시킬 수 있다.
- 대표적인 예
  - `OutofMemoryError`: 가상 머신에 할당된 메모리가 부족하여 발생하는 `Error`.
  - `StackOverflowError`: 스택 오버플로우 때문에 발생하는 `Error`.
  - `VirtualMachineError`: JVM과 관련된 문제로 발생하는 `Error`.
## Exception
- `Exception`(`java.lang.Exception`) 클래스는 `Throwable`을 상속받아 자바 실행중 발생할 수 있는 일반적인 오류(예외)를 구현한다.
- `Exception`은 크게 `RuntimeException`을 상속받는 것과 그렇지 않은 것 2가지로 분류가능하다.
- 사용자 정의 오류가 필요하다면 `Exception`혹은 `RuntimeException`을 상속받아 사용하는 것이 바람직하다.
### RuntimeException 인 것
- *Unchecked Throwable*로, 별도의 명시 없이 발생시킬 수 있다.
- 대표적인 예
  - `NullPointerException`: 참조 타입의 변수가 가르키고 있는 힙 주소가 없는경우(`null`)에서 멤버에 접근하려 했을 때 발생한다.
  - `IndexOutOfBoundsException`: 배열 등에서 그 순번을 벗어나 인덱스에 대해 접근하려 했을 때 발생한다.
  - `ArithmeticException`: 수학 및 사칙연산과 관련하여 문제가 있을 때 발생한다.
### RuntimeException 이 아닌 것
- *Checked Throwable*로, 별도의 명시 없이 호출/발생시킬 수 **없다**.
- 대표적인 예
  - `IOException`: 파일 등의 입/출력과 관련하여 문제가 있을 때 발생한다.
  - `InterruptedException`: 사용자 및 기타 주체에 의해 스레드 등의 실행이 중단(방해)되었을 때 발생한다.
  - `TimeOutException`: 어떠한 작업에 대해 제한된 시간이 초과되었을 때 발생한다.
## Throwable 발생시키기
- 고의로 `Throwable`을 발생시키기 위해 `throw`키워드를 활용할 수 있다.
>```
> throw [Throwable을 상속 받는 객체];
>```
>- 가령, `ArithmeticException`을 발생시키기 위해 아래와 같이 작성한다.
>```
> throw new ArithmeticException();
>```
- 단, 던지고자 하는 타입이 **Checked**라면, 던지는 구문이(Try-Catch 혹은 `throws`키워드를 통해) 적절하게 처리되어야 한다.