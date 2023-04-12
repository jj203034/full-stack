package com.jjlee.study_jdbc.services;

import com.jjlee.study_jdbc.daos.MemoDao;
import com.jjlee.study_jdbc.entities.MemoEntity;

import java.util.List;

public class MemoService {
    private final MemoDao memoDao = new MemoDao();
    public int getCount() throws Exception {
        return this.memoDao.selectCount();
    }

    public List<MemoEntity> query(int page) throws Exception{
        return this.memoDao.select(page);
    }
    public boolean write(MemoEntity memo) throws Exception {
        if (memo.getText().length() == 0 || memo.getText().length() > 1000) {
            return false;
        }
        return this.memoDao.insert(memo) > 0;
    }
}
