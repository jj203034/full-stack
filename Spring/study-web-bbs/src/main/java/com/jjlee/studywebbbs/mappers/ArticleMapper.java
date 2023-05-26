package com.jjlee.studywebbbs.mappers;

import com.jjlee.studywebbbs.entities.ArticleEntity;
import com.jjlee.studywebbbs.entities.AttachmentEntity;
import com.jjlee.studywebbbs.entities.CommentEntity;
import com.jjlee.studywebbbs.entities.ImageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper {
    int insertArticle(ArticleEntity article);

    int insertAttachment(AttachmentEntity attachment);

    int insertComment(CommentEntity comment);

    int insertImage(ImageEntity image);

    ArticleEntity selectArticleByIndex(@Param(value = "index")int index);

    AttachmentEntity selectAttachment(@Param(value = "index")int index);

    AttachmentEntity[] selectAttachmentsByArticleIndexNoData(@Param(value = "articleIndex")int articleIndex);

    CommentEntity[] selectCommentsByArticleIndex(@Param(value = "articleIndex")int articleIndex);

    ImageEntity selectImage(@Param(value = "index")int index);

    int updateArticle(ArticleEntity article);
}
