package com.website.entity;

import lombok.Data;

@Data
public class Comment {
    String commentId;
    String replyId;

    String comment;
    String createdTime;
    int userId;
    String articleId;
    int likes;
}
