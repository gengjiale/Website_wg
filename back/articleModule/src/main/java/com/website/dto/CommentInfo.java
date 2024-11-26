package com.website.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommentInfo {
    String commentId; // 自己的id;
    List<CommentInfo> replies = new ArrayList<>(); // 子评论

    String comment;
    String createdTime;
    int userid;
    String userName;
    String articleId;
    int likes;

    String author;
    String date;
    String content;
    String id;
}
