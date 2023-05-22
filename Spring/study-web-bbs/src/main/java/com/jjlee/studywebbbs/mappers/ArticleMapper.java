package com.jjlee.studywebbbs.mappers;

import com.jjlee.studywebbbs.entities.ArticleEntity;
import com.jjlee.studywebbbs.entities.AttachmentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper {
    int insertArticle(ArticleEntity article);

    int insertAttachment(AttachmentEntity attachment);

    ArticleEntity selectArticleByIndex(@Param(value = "index")int index);

    AttachmentEntity[] selectAttachmentsByArticleIndexNoData(@Param(value = "articleIndex")int articleIndex);

    int updateArticle(ArticleEntity article);
}
