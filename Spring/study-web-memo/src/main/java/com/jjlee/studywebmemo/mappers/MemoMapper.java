package com.jjlee.studywebmemo.mappers;

import com.jjlee.studywebmemo.entities.MemoEntity;
import com.jjlee.studywebmemo.models.PagingModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemoMapper {
    int insert(MemoEntity memoEntity);

    MemoEntity[] selectAll();

    int selectAllCount();

    MemoEntity[] selectByPage(PagingModel pagingModel);
}
