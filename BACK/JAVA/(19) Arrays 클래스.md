# Arrays
- `Arrays`(`java.util.Arrays`)클래스는 자바가 가지고 있는 배열(`~[]`)에 대한 편의기능을 제공한다.
## 정적 메서드
- `asList(T...ts)`: 전달받은 가변인자 `ts`가 가지는 인자를 인자로 가지는 `List<t>`타입의 객체를 반환한다.
- `equals(T[] ta, T[] tb)`: 전달받은 어떠한 타입의 배열 `ta`와 `tb`가 가지는 인자가 전체 일치하는가의 여부를 반환한다.
- `fill(T[] t, T v)`: 배열 `t`가 가지는 모든 인자의 값을 `v`로 설정한다.
- `sort(T[] t)`: 숫자배열 `t`가 가지는 인자를 오름차순 정렬한다.
- `<T>sort(T[] t, Comparator<? super T> c)`: 참조타입 `T`에대한 배열 `t`가 가지는 인자를 비교자 `c`가 제시하는 기준으로 정렬한다.
  - `java.util.Collections.reverseOrder()`메서드는 내림차순 정렬을 할 수 있는 비교자를 반환한다.
- `toString(T[] t)`: 배열 `t`가 가지는 인자를 나열된 형태의 문자열로 반환한다.