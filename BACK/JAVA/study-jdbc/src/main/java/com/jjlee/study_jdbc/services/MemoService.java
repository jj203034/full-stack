package com.jjlee.study_jdbc.services;

import com.jjlee.study_jdbc.daos.MemoDao;
import com.jjlee.study_jdbc.entities.MemoEntity;

public class MemoService {
    private final MemoDao memoDao = new MemoDao();
    public boolean write(MemoEntity memo) throws Exception {
        if (memo.getText().length() == 0 || memo.getText().length() > 1000) {
            return false;
        }
        return this.memoDao.insert(memo) > 0;
    }
}
