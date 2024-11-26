package com.website.service;

import com.website.dto.ArticleInfo;
import com.website.entity.Article;
import com.website.mapper.ArticleMapper;
import com.website.mapper.CollectMapper;
import com.website.mapper.LikeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class InterFunction {
    LikeMapper likeMapper;
    CollectMapper collectMapper;
    ArticleMapper articleMapper;
    ArticleService articleService;

    public List<ArticleInfo> getLikeList(int id){
        List<String> list = likeMapper.selectArticleIdListByUserId(id);
        List<ArticleInfo> articleInfoList = new ArrayList<>();
        for (int i = 0; i < list.size() ; i++){
            Article article = articleMapper.selectById(list.get(i));
            articleInfoList.add(articleService.setArticleToArticleInfo(article));
        }
        return articleInfoList;
    }

    public ArticleInfo likeArticle(int userid, String articleId){
        int i = likeMapper.insert(userid, articleId);
        if (i > 0){;
            Article article = articleMapper.selectById(articleId);
            return articleService.setArticleToArticleInfo(article);
        }else{
            return null;
        }
    }

    public void cancelLike(int userid, String articleId){
        likeMapper.delete(userid,articleId);
    }

    public List<ArticleInfo> getCollectList(int id){
        List<String> list = collectMapper.selectArticleIdListByUserId(id);
        List<ArticleInfo> articleInfoList = new ArrayList<>();
        for (int i = 0; i < list.size() ; i++){
            Article article = articleMapper.selectById(list.get(i));
            articleInfoList.add(articleService.setArticleToArticleInfo(article));
        }
        return articleInfoList;
    }

    public ArticleInfo collectArticle(int userid, String articleId){
        int i = collectMapper.insert(userid, articleId);
        if (i > 0){;
            Article article = articleMapper.selectById(articleId);
            return articleService.setArticleToArticleInfo(article);
        }else{
            return null;
        }
    }

    public void cancelCollect(int userid, String articleId){
        collectMapper.delete(userid,articleId);
    }

}
