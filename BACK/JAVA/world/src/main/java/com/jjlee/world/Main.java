package com.jjlee.world;

import com.jjlee.world.animals.Eagle;
import com.jjlee.world.animals.Flyable;

public class Main {
    public static void main(String[] args) {
        Country korea = new Country();
        korea.name = "대한민국";
        korea.continent = Continent.ASIA;
        korea.population = 51_414_281L;
        korea.area = 100_210D;

        System.out.println(korea.continent.nameKo);
    }
    // toCamel 메서드는 입력받은 문자열 input의 내용에 대해서 카멜 케이스를 적용시켜 반환하는 메서드이다.
    // 가령, toCamel("Very nice to see you") 를 전달할 경우 반환값은 "veryNiceToSeeYou"가 되면된다.
    // 단, 입력값 input은 영어 소문자, 대문자 및 공백만 포함하는 것으로 한다. 또한, 붙은단어 2개 이상은 하나의 단어로 인식하며, 단어간 구분은 공백으로만 판단하도록 한다.
    // 가령, toCamel("AppleBanana Orange")의 입력값에서 Apple과 Banana는 2개의 단어라는 것을 인지하지만, 로직상 하나의 단어로 판단하고(사이에 공백이 없으므로),
    // 반환값은 "applebanana Orange"가 되면 된다.
//    public static String toCamel(String input) {
//        String output = ""; // 반환할 결과 값을 담는 문자열
//        final byte caseFactor = 32; // 32는 대/소문자간 전환을 위해 필요한 ASCII 코드 차이다. 상수로 지정해 놓고 유용.
//        char[] inputArray = input.strip().toCharArray(); // input 문자열이 가지고 있는 문자 배열
//        for (int i = 0; i < inputArray.length; i++) {
//            char c = inputArray[i]; // 하나하나 씹어볼 문자
//            boolean isLower = c >= 97 && c <= 122; // 대문자 여부
//            boolean isUpper = c >= 65 && c <= 90; // 소문자 여부
//            if (!isLower && !isUpper) { // 대문자도 아니"고" 소문자도 아니면, 영어가 아니라는 뜻임으로 패스
//                continue;
//            }
//            if (i == 0 && isUpper) { // 첫자이면서 대문자이면
//                c = (char) (c + caseFactor); // 소문자화
//            } else if (i > 0 && inputArray[i - 1] == ' ' && isLower) { // 첫자는 아닌데, 바로 앞자가 공백( )이고 소문자이면
//                c = (char) (c - caseFactor); // 대문자화
//            }
//            output += c; // 이어 붙이기(사실 이 시점에서는 위에서 영어가 아닌걸 다 건너 뛰었기 때문에 소문자 아님 대문자 밖에 없음)
//        }
//        return output; // 반환
//    }

    //toCamel 메서드가 입력받는 input은 기존에는 영어 소문자/대문자만 입력된다는 약속이 있었으나, 소문자/대문자가 아닌 문자열도 함께 들어올수 있다고 가정한다.
    // 그 어떤 입력값에 대해서도 반환값에 대해서는 영어 소문자/대문자만 카멜 케이스화 하여 반환할 수 있도록 하라.
    // toCamel("What a WONDERFUL day!!!") -> "whatAWonderfulDay"
    // toCamel("반갑습니다. John이라고 해요.) -> "john"
    // toCamel("한국(韓國)의 면적은 100210km2이다.") -> "km"


    // 가로 길이가 w, 세로 길이가 h, 깊이가 d인 정(직)육면체<c>에 대해 반지름이 r인 구<g>를 제외한 부피를 반환하는 메서드 calc를 완성하시오.
//    public static double calc(int w, int h, int d, int r) {
//        double result = 0D;
//        result = w*h*d - (4D/3)*Math.pow(r,3)*Math.PI;
//        return result;
//    }

    // 좌표평면상에 있는 두 지점 a와 b가 가지는 좌표를 각 (x1, y1), (x2, y2)라고 할 때 두 좌표간의 거리를 반환하는 메서드 calc를 만드세요.
    // 단, 각 좌표는 음수일 수 있음
//    public static double calc(int x1, int x2, int y1, int y2) {
//        double result = 0D;
//        result = Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1),2));
//        return result;
//    }

    // 이차방정식의 각 계수 a와b, 그리고 상수 c가 주어졌을때 해당 방정식의 근을 반환하는 메서드 calc를 완성하시오.
    // 실근이 없는경우 길이가 0인 double[]을 반환
    // 중근일 경우 길이가 1인 double[]을 반환
    // 서로다른 실근이 있는 경우 길이가 2인 double[]을 반환하되, 더 작은값이 먼저 오도록 반환
//    public static double[] calc(int a, int b, int c) {
//        double[] result;
//        double d = Math.pow(b, 2) - 4D * a * c;
//        if (d < 0D) {
//            result = new double[0];
//        } else if (d > 0D) {
//            double f = -b / (2D * a);
//            double v = Math.sqrt(d) / (2D * a);
//            double x1 = f + v;
//            double x2 = f - v;
////            result = new double[] {Math.min(x1,x2), Math.max(x1,x2)};
//            result = x1 > x2
//                    ? new double[]{x2,x1}
//                    : new double[]{x1,x2};
//        } else {
//            result = new double[] { -b / (2D * a)};
//        }
//        return result;
//    }
        // 전달된 문자열 s가 포함하고 있는 문자중, 숫자만을 변환대상으로 한다.
        // ex) parseInt("Hello korea2023!") 호출 결과는 정수인 2023이면 된다.
        // 추가로, 선행하는 0은 신경쓰지 않는다.
//    public static int parseInt(String s) {
//        String output = "";
//        char[] sArray = s.toCharArray();
//        for (int i=0; i < sArray.length; i++) {
//            char c = sArray[i];
//            if (c>= 48 && c<= 57) {
//                output += c;
//            }
//        }
//        return Integer.parseInt(output);
//    }

    // Try-Catch ex
//    String s = "Hello";
//        System.out.println("시작");
//        try {
//        int i = Integer.parseInt(s);
//        System.out.println("성공!");
//    } catch (NumberFormatException nfe) {
//        System.out.println("실패!");
//    } finally {
//        System.out.println("종료!");
//    }

}