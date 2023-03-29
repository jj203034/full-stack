## Flex
- 플렉스(Flex)는 이가 가지는 자식 요소들에 대한 정렬을 쉽게해준다.
- 컨테이너 요소의 `display` CSS 속성 값을 `flex`(혹은 `inline-flex`)로 함으로써 해당 요소의 자식들에 대한 정렬을 시작할 수 있다.
## 부모 속성
### align-content
- 플렉스 박스 전체에 대한 아이템들의 정렬이다.
- 속성 값:
  - `center`: 가운데 정렬
  - `flex-start`: 시작 부분 정렬
  - `flex-end`: 끝 부분 정렬
  - `space-around`: 각 줄 사이의 공간은 같으나 처음과 끝 여유 공간은 각 줄 사이 공간의 절반
  - `space-between`: 각 줄 사이의 공간을 같게 하고 처음과 끝은 여유 공간이 없음.
  - `space-evenly`: 각 줄 사이의 공간과 양 끝 공간이 동일.
  - `stretch`
### align-items
- `flex-direction`에서 설정된 방향에 수직한 방향으로의 정렬에 대한 설정이다.
- 속성 값:
  - `center`: 가운데 정렬
  - `flex-start`: 시작 부분 정렬
  - `flex-end`: 끝 부분 정렬
  - `stretch`: 늘이기
### flex-direction
- 컨테이너가 가지는 자식 요소들이 정렬되는 방향이다.
- 기본 값: `row`
- 속성 값
  - `row`: 행(가로)방향 정렬
  - `row-reverse`: `row`방향 역순 정렬
  - `column`: 열(세로)방향 정렬
  - `column-reverse`: `column`방향 역순 정렬
### flex-wrap
- 컨테이너의 범위를 벗어나는 자식에 대한 개행 설정이다.
- 기본 값: `nowrap`
- 속성 값
  - `nowrap`: 개행하지 않음
  - `wrap`: 개행함
### justify-content
- `flex-direction`에서 설정된 방향에 수평한 방향으로의 정렬에 대한 설정이다.
- 속성 값:
  - `center`: 가운데 정렬
  - `flex-start`: 시작 부분 정렬
  - `flex-end`: 끝 부분 정렬
## 자식 속성
### align-self
- 부모에서 지정한 `align-items`를 무시하고 본 요소에 대한 정렬을 별도로 지정한다.
- 속성 값은 `align-items`와 동일하다.
### flex
- 늘어나고(`flex-grow`), 줄어드는(`flex-shrink`) 비율 및 `flex-basis`(기본 크기)를 지정한다.
- 기본 값: `0` `0` `auto`
- 속성 구조: `[flex-grow 속성 값] [flex-shrink 속성 값] [flex-basis 속성 값]`
#### flex-grow
- 해당 아이템이 늘어나는 비율이다.
- 속성 값: `[숫자]`
#### flex-shrink
- 해당 아이템이 줄어드는 비율이다.
- 속성 값: `[숫자]`
#### flex-basis
- 해당 아이템의 기본 크기이다.
- `flex-direction`이 `row`일때 해당 속성은 `width`와 같다.
- `flex-direction`이 `column`일때 해당 속성은 `height`와 같다.
