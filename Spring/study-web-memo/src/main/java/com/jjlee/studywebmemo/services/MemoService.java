package com.jjlee.studywebmemo.services;

import com.jjlee.studywebmemo.entities.MemoEntity;
import com.jjlee.studywebmemo.mappers.MemoMapper;
import com.jjlee.studywebmemo.models.PagingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MemoService {
    public static final int PAGE_COUNT = 10;
    private final MemoMapper memoMapper;
    @Autowired
    public MemoService(MemoMapper memoMapper) {
        this.memoMapper = memoMapper;
    }
    public MemoEntity[] getAll() {
        return this.memoMapper.selectAll();
    }
    public int getAllCount() {
        return this.memoMapper.selectAllCount();
    }

    public MemoEntity[] getByPage(PagingModel pagingModel) {
        MemoEntity[] memoEntities = this.memoMapper.selectByPage(pagingModel);
        return memoEntities;
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
