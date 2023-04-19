package com.jjlee.studywebmemo.mappers;

import com.jjlee.studywebmemo.entities.MemoEntity;
import com.jjlee.studywebmemo.models.PagingModel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemoMapper {
    //@Delete("DELETE FROM `study_web`.`memos` WHERE `index` = #{index} LIMIT 1")
    int insert(MemoEntity memoEntity);

    MemoEntity[] selectAll();

    int selectCount(@Param(value = "searchCriterion")String searchCriterion,
                    @Param(value = "searchQuery")String searchQuery);

    // @Param 사용시 ParameterType 사용안해도됨
    MemoEntity[] selectByPage(@Param(value = "pagingModel")PagingModel pagingModel,
                              @Param(value = "searchCriterion")String searchCriterion,
                              @Param(value = "searchQuery")String searchQuery);

    int updateByIndex(@Param(value = "index") int index,
                      @Param(value = "text") String text);

    int deleteByIndex(@Param(value = "index") int index);
}
