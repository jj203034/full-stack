package com.jjlee.world;

public class Main {
    public static void main(String[] args) {
        System.out.println(toCamel("Very nice to see you"));
    }
    // toCamel 메서드는 입력받은 문자열 input의 내용에 대해서 카멜 케이스를 적용시켜 반환하는 메서드이다.
    // 가령, toCamel("Very nice to see you") 를 전달할 경우 반환값은 "veryNiceToSeeYou"가 되면된다.
    // 단, 입력값 input은 영어 소문자, 대문자 및 공백만 포함하는 것으로 한다. 또한, 붙은단어 2개 이상은 하나의 단어로 인식하며, 단어간 구분은 공백으로만 판단하도록 한다.
    // 가령, toCamel("AppleBanana Orange")의 입력값에서 Apple과 Banana는 2개의 단어라는 것을 인지하지만, 로직상 하나의 단어로 판단하고(사이에 공백이 없으므로),
    // 반환값은 "applebanana Orange"가 되면 된다.
    public static String toCamel(String input) {
        String output = ""; // 반환할 결과 값을 담는 문자열
        final byte caseFactor = 32; // 32는 대/소문자간 전환을 위해 필요한 ASCII 코드 차이다. 상수로 지정해 놓고 유용.
        char[] inputArray = input.strip().toCharArray(); // input 문자열이 가지고 있는 문자 배열
        for (int i = 0; i < inputArray.length; i++) {
            char c = inputArray[i]; // 하나하나 씹어볼 문자
            boolean isLower = c >= 97 && c <= 122; // 대문자 여부
            boolean isUpper = c >= 65 && c <= 90; // 소문자 여부
            if (!isLower && !isUpper) { // 대문자도 아니"고" 소문자도 아니면, 영어가 아니라는 뜻임으로 패스
                continue;
            }
            if (i == 0 && isUpper) { // 첫자이면서 대문자이면
                c = (char) (c + caseFactor); // 소문자화
            } else if (i > 0 && inputArray[i - 1] == ' ' && isLower) { // 첫자는 아닌데, 바로 앞자가 공백( )이고 소문자이면
                c = (char) (c - caseFactor); // 대문자화
            }
            output += c; // 이어 붙이기(사실 이 시점에서는 위에서 영어가 아닌걸 다 건너 뛰었기 때문에 소문자 아님 대문자 밖에 없음)
        }
        return output; // 반환
    }
}