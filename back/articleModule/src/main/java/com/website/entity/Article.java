package com.website.entity;

import lombok.Data;

@Data
public class Article {
    String id; // id
    int userid; // 作者id
    String title; // 标题
    String content; // 内容（目前只有文本形式）
    String description; // 描述
    String publish_time; // 发布时间
    String state; // 文章状态
    int comment_count; // 评论个数
    int like_count; // 点赞个数
    int collect_count; // 收藏个数
    int share_count; // 分享个数
    int tag;
}
