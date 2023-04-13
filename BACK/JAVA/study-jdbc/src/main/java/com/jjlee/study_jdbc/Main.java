package com.jjlee.study_jdbc;

import com.jjlee.study_jdbc.controllers.MemoController;
import com.jjlee.study_jdbc.entities.MemoEntity;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception{
        while (true) {
            System.out.println("=".repeat(50));
            System.out.println("명령을 선택해 주세요.");
            System.out.println("=".repeat(50));
            System.out.println("1. 메모");
            System.out.println("q. 종료");
            System.out.println("=".repeat(50));
            System.out.print("> ");
            String command = Main.scanner.nextLine();
            switch (command) {
                case "1":
                    Main.handleMemo();
                    break;
                case "q":
                    System.out.println("프로그램을 종료.");
                    return;
                default:
                    System.out.printf("'%s'은(는) 올바르지 않은 명령입니다.\n", command);
            }
        }
    }

    private static void handleMemo() throws Exception {
        MemoController memoController = new MemoController();
        while (true) {
            System.out.println("=".repeat(32));
            System.out.println("메모");
            System.out.println("=".repeat(32));
            System.out.println("1. 작성하기");
            System.out.println("2. 조회하기");
            System.out.println("3. 삭제하기");
            System.out.println("q. 돌아가기");
            System.out.println("=".repeat(32));
            System.out.print("> ");
            String command = Main.scanner.nextLine();
            switch (command) {
                case "1":
                    if (memoController.write(Main.scanner)) {
                        System.out.println("성공적으로 메모를 작성하였습니다.");
                    } else {
                        System.out.println("알 수 없는 이유로 메모를 작성하지 못하였습니다.");
                    }
                    break;
                case "2":
                    List<MemoEntity> memos = memoController.query(Main.scanner);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    System.out.println("=".repeat(32));
                    System.out.printf("No.\tDt.                \tText\n");
                    System.out.println("=".repeat(32));
                    if (memos.size() == 0) {
                        System.out.println("표시할 메모가 없습니다.");
                    } else {
                        for (MemoEntity memo : memos) {
                            System.out.printf("%3d\t%s\t%s\n",
                                    memo.getIndex(),
                                    sdf.format(memo.getDate()),
                                    memo.getText());
                        }
                        System.out.println("=".repeat(32));
                        System.out.printf("메모 총 %,d개\n", memos.size());
                        System.out.println("=".repeat(32));
                    }
                    break;
                case "3":
                    if (memoController.delete(Main.scanner)) {
                        System.out.println("메모를 성공적으로 삭제하였습니다.");
                    } else {
                        System.out.println("메모를 삭제하지 못하였습니다.");
                    }
                    break;
                case "q":
                    return;
                default:
                    System.out.printf("'%s'은(는) 올바르지 않은 명령입니다.\n", command);
            }
        }
    }
}
    // Entity
//    public static void main(String[] args) throws Exception{
//        MemoEntity memo1 = new MemoEntity()
//                .setIndex(0)
//                .setText("asdsadasd");
//        MemoEntity memo2 = new MemoEntity()
//                .setIndex(0)
//                .setText("asdsadasd");
//        System.out.println(memo1.equals(memo2));
//    }
    // ex)
//    public static void main(String[] args) {
//        try {
//            Class.forName("org.mariadb.jdbc.Driver");
//            //id, pw, port, host
//            Connection connection = DriverManager.getConnection(
//                    "jdbc:mariadb://localhost:33063",   // (jdbc:mariadb://) -> 프로토콜
//                    "study",
//                    "test1234");
//            try (connection) {
//                String query = "SELECT 1 AS `value`";
//                PreparedStatement preparedStatement = connection.prepareStatement(query);
//                try (preparedStatement) {
//                    ResultSet resultSet = preparedStatement.executeQuery(); // query가 SELECT일 때만 ResultSet 사용
//                    try (resultSet) {
//                        int value = -1;
//                        while (resultSet.next()) {
//                            value = resultSet.getInt("value");
//                        }
//                        System.out.println(value);
//                    }
//                }
//            }
//        } catch (ClassNotFoundException e) {
//            System.out.println("접속 실패!");
//            System.out.println(e.getMessage());
//        } catch (SQLException e) {
//            System.out.println("접속 실패!");
//            System.out.println(e.getMessage());
//        }
//    }
