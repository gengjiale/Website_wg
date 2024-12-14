package com.website.entity;

import lombok.Data;

@Data
public class Article {
    String articleId; // id
    String userId; // 作者id
    String title; // 标题
    String content; // 内容
    String description; // 描述
    String publishTime; // 发布时间
    int commentCount; // 评论个数
    int likeCount; // 点赞个数
    int collectCount; // 收藏个数
    String picture;
}
