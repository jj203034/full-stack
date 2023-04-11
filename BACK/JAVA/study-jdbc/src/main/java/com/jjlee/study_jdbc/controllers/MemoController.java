package com.jjlee.study_jdbc.controllers;

import com.jjlee.study_jdbc.entities.MemoEntity;
import com.jjlee.study_jdbc.services.MemoService;

import java.util.Date;
import java.util.Scanner;

public class MemoController {
    private final MemoService memoService = new MemoService();
    public boolean write(Scanner scanner) throws Exception{
        System.out.println("=".repeat(50));
        System.out.println("메모 작성");
        System.out.println("=".repeat(50));
        System.out.print("내용 : ");
        String text = scanner.nextLine();
        MemoEntity memo = new MemoEntity()
                .setDate(new Date())
                .setText(text);
        return this.memoService.write(memo);
    }
}
