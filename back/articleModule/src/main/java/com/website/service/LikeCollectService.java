package com.website.service;

import com.website.entity.Collect;
import com.website.entity.Like;
import com.website.mapper.ArticleMapper;
import com.website.mapper.CollectMapper;
import com.website.mapper.LikeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeCollectService {
    ArticleMapper articleMapper;
    LikeMapper likeMapper;
    CollectMapper collectMapper;

    public boolean isUserLike(String articleId, String userId){
        Like like = likeMapper.selectByArticleIdAndUserId(articleId, userId);
        return like != null;
    }

    public void likeArticle(String articleId, String userId){
        likeMapper.insert(userId, articleId);
        articleMapper.likeArticle(articleId);

    }

    public void cancelLike(String articleId, String userId){
        likeMapper.delete(userId, articleId);
        articleMapper.cancelLike(articleId);
    }



    public boolean isUserCollect(String articleId, String userId){
        Collect collect = collectMapper.selectByArticleIdAndUserId(articleId, userId);
        return collect != null;
    }

    public void collectArticle(String articleId, String userId){
        collectMapper.insert(userId, articleId);
        articleMapper.collectArticle(articleId);
    }

    public void cancelCollect(String articleId, String userId){
        collectMapper.delete(userId, articleId);
        articleMapper.cancelCollect(articleId);
    }

}
