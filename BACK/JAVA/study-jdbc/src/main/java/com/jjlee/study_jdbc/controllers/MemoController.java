package com.jjlee.study_jdbc.controllers;

import com.jjlee.study_jdbc.entities.MemoEntity;
import com.jjlee.study_jdbc.services.MemoService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MemoController {
    private final MemoService memoService = new MemoService();
    public boolean delete(Scanner scanner) throws Exception{
        while (true) {
            System.out.println("=".repeat(32));
            System.out.println("메모 삭제");
            System.out.println("=".repeat(32));
            System.out.print("삭제할 메모 번호 : ");
            String indexStr = scanner.nextLine();
            int index;
            try {
                index = Integer.parseInt(indexStr);
            } catch (NumberFormatException ignored) {
                System.out.println("올바른 메모 번호를 입력해 주세요.");
                continue;
            }
            return this.memoService.delete(index);
        }
    }
    public List<MemoEntity> query(Scanner scanner) throws Exception{
        while (true) {
            System.out.println("=".repeat(32));
            System.out.println("메모 조회");
            System.out.println("=".repeat(32));
            final int countPerPAge = 10;
            final int totalCount = this.memoService.getCount();
            final int maxPage = totalCount / countPerPAge + (totalCount % countPerPAge == 0 ? 0 : 1);
            System.out.printf("전체 메모 개수 : %,d\n", totalCount);
            System.out.printf("마지막 페이지 : %,d\n", maxPage);
            System.out.print("조회할 페이지 : ");
            String pageStr = scanner.nextLine();
            int page;
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException ignored) {
                System.out.println("올바른 숫자를 입력해 주세요.");
                continue;
            }
            if (page < 1 || page > maxPage) {
                System.out.println("조회할 수 있는 페이지 범위를 벗어났습니다.");
                continue;
            }
            return this.memoService.query(page);
        }
    }
    public boolean write(Scanner scanner) throws Exception{
        System.out.println("=".repeat(50));
        System.out.println("메모 작성");
        System.out.println("=".repeat(50));
        System.out.print("내용 : ");
        String textStr = scanner.nextLine();
        MemoEntity memo = new MemoEntity()
                .setDate(new Date())
                .setText(textStr);
        return this.memoService.write(memo);
    }
}
