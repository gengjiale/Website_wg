package com.website.entity;

import lombok.Data;

@Data
public class Comment {
    String commentId;
    String replyId;
    String content;
    String createdTime;
    String userId;
    String articleId;
    int likeCount;
}
