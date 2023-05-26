package com.jjlee.studywebbbs.services;

import com.jjlee.studywebbbs.entities.ArticleEntity;
import com.jjlee.studywebbbs.entities.AttachmentEntity;
import com.jjlee.studywebbbs.entities.CommentEntity;
import com.jjlee.studywebbbs.entities.ImageEntity;
import com.jjlee.studywebbbs.mappers.ArticleMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@Service
public class ArticleService {
    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleService(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    public ArticleEntity readArticle(int index) {
        ArticleEntity article = this.articleMapper.selectArticleByIndex(index);
        if (article != null && !article.isDeleted()) {
            article.setView(article.getView() + 1);
            this.articleMapper.updateArticle(article);
        }
        return article;
    }

    public AttachmentEntity[] getAttachmentsOf(ArticleEntity article) {
        return this.articleMapper.selectAttachmentsByArticleIndexNoData(article.getIndex());
    }

    @Transactional
    public boolean putArticle(HttpServletRequest request,
                              ArticleEntity article,
                              MultipartFile[] files) throws IOException {
        article.setView(0)
                .setCreatedAt(new Date())
                .setClientIp(request.getRemoteAddr())
                .setClientUa(request.getHeader("User-Agent"))
                .setDeleted(false);
        if (this.articleMapper.insertArticle(article) < 1) {
            return false;
        }
        int inserted = 0;
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            AttachmentEntity attachment = new AttachmentEntity();
            attachment.setArticleIndex(article.getIndex())
                    .setFileData(file.getBytes())
                    .setFileContentType(file.getContentType())
                    .setFileSize(file.getSize())
                    .setFileName(file.getOriginalFilename());
            inserted += this.articleMapper.insertAttachment(attachment);
        }
        return inserted == files.length;
    }

    public ImageEntity putImage(HttpServletRequest request, MultipartFile file) throws IOException {
        ImageEntity image = new ImageEntity();
        image.setName(file.getOriginalFilename())
                .setSize(file.getSize())
                .setContentType(file.getContentType())
                .setData(file.getBytes())
                .setCreatedAt(new Date())
                .setClientIp(request.getRemoteAddr())
                .setClientUa(request.getHeader("User-Agent"));
        this.articleMapper.insertImage(image);
        return image;
    }

    public boolean putComment(HttpServletRequest request ,CommentEntity comment) {
        comment.setDeleted(false)
                .setCreatedAt(new Date())
                .setClientIp(request.getRemoteAddr())
                .setClientUa(request.getHeader("User-Agent"));
        return this.articleMapper.insertComment(comment) > 0;
    }

    public ImageEntity getImage(int index) {
        return this.articleMapper.selectImage(index);
    }

    public AttachmentEntity getAttachment(int index) {
        return this.articleMapper.selectAttachment(index);
    }

    public CommentEntity[] getCommentsOf(int articleIndex) {
        return this.articleMapper.selectCommentsByArticleIndex(articleIndex);
    }
}
