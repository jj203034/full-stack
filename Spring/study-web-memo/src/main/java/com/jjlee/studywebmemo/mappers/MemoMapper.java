package com.jjlee.studywebmemo.mappers;

import com.jjlee.studywebmemo.entities.MemoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemoMapper {
    int insert(MemoEntity memoEntity);
}
