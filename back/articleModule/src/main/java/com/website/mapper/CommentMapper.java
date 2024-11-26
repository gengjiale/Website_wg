package com.website.mapper;

import com.website.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {
    // 查询，按创建时间升序查询
    List<Comment> getCommentsByArticleId(String id); //
    Comment getById(String id); //

    //增加
    int insertComment(String commentid, String replyid, String comment, String createdTime, String articleId, int userId);

    //删除
    int deleteComment(String id);
}
