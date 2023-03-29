public class MyFirstClass {
//    public static int num = 5;

    public static void main(String[] args) {
        //1부터 100까지의 정수중 홀수들의 합을 출력
//        int sum = 0;
//
//        for (int i = 1; i <= 100; i++) {
//            if (i % 2 != 0) {
//                sum += i;
//            }
//        }
//
//        System.out.println(sum);

        // 콜라츠 추축
        // 콜라츠 추축은 어떠한 자연수에 대해 그 수가 홀수이면 3을 곱하고 1을 더하고, 짝수이면 2로 나누는 과정을 반복할 경우 n회차 후 해당 수가 반드시 1이 될거라는 추측이다.
        // 가령 숫자 7을 예로 들었을때 아래와 같은 연산 과정을 거친다.
        // 7 → 22 → 11 → 34 → 17 → 52 → 26 → 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
        // 즉 숫자 7을 16회(n=16)의 연산을 거쳐 1이 되어 콜라츠 추측에 부합한수이다.
        // 이 때, num이 몇회만에 1이되어 콜라츠 추측에 부합하는지 출력하라
//        long num = 3141592L;
//        int count = 0;
//        while (num != 1) {
//            if (num % 2 == 0) {
//                num /= 2;
//            } else {
//                num = num * 3 + 1;
//            }
//            count++;
//        }
//
//        System.out.println(count);

        //스택과 힙영역
//        int[] nums = new int[] {3, 2, 3};
//        int[] b = nums;
//        b[1] = 99;
//        System.out.println(nums);

        //정적 비정적
//        System.out.println(Math.PI);
        // System = 클래스
        // out = 정적(System을 통했기 때문), 읽기 전용
        // println = 메서드

        // 1. PI는 정적이다. (Math를 통했고 대문자 스네이크 케이스이기 때문)
        // 2. PI는 읽기 전용이다.(대문자 스네이크 케이스 때문)

//        System.out.println(MyFirstClass.num);
//        MyFirstClass.doA();
//        System.out.println(MyFirstClass.num);
//        MyFirstClass.doB();
//        System.out.println(MyFirstClass.num);

        // 멤버변수와 지역변수
        // static(정적인 것)에 접근할때는 클래스를 통해야한다.
        Human kim = new Human(); // kim -> 지역변수
        kim.name = "김"; // name -> 멤버변수
        kim.age = 25;

        Human lee = new Human();
        lee.name = "이";
        lee.age = 22;

        System.out.println(kim.name); // 김
        System.out.println(lee.name); // 이
    }
    // 정적 비정적
//    public static void doA() {
//        MyFirstClass.num = 10;
//    }
//    public static void doB() {
//        MyFirstClass.num = 15;
//    }
}
