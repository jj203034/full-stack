package com.jjlee.studywebmemo.services;

import com.jjlee.studywebmemo.entities.MemoEntity;
import com.jjlee.studywebmemo.mappers.MemoMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MemoService {
    private final MemoMapper memoMapper;

    public MemoService(MemoMapper memoMapper) {
        this.memoMapper = memoMapper;
    }

    public boolean write(MemoEntity memoEntity) {
        if (memoEntity.getNickname() == null ||
                memoEntity.getText() == null ||
                memoEntity.getText().length() < 1 ||
                !memoEntity.getNickname().matches("^([가-힣]{2,10})$")) {
            System.out.println("정규식 실패!");
            return false;
        }
        memoEntity.setDatetime(new Date());
        return this.memoMapper.insert(memoEntity) > 0;
    }
}
