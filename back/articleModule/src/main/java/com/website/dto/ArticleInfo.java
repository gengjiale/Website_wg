package com.website.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ArticleInfo {
    String articleId; // 文章id
    String userId; // 作者id
    String title; // 标题
    String content; // 内容（目前只有文本形式）
    String description; // 描述
    String publishTime; // 发布时间
    int commentCount; // 评论个数
    int likeCount; // 点赞个数
    int collectCount; // 收藏个数
    String picture; // 封面地址

    List<String> tagList; // tags
    String userName; // 作者名

    boolean userLike;
    boolean userCollect;

}
