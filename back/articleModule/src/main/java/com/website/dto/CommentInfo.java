package com.website.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommentInfo {
    String commentId; // 自己的id;
    String replyId;
    String content;
    String createdTime;
    String userId;
    String articleId;

    List<CommentInfo> replies = new ArrayList<>(); // 子评论

    String userName;

    int likeCount;

    boolean replyFlag = false;

}
