package com.website.service;

import com.website.dto.ArticleInfo;
import com.website.entity.Article;
import com.website.mapper.ArticleMapper;
import com.website.mapper.CollectMapper;
import com.website.mapper.LikeMapper;
import com.website.mapper.TagMapper;
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

    TagMapper tagMapper;

    public List<ArticleInfo> getLikeList(String id){
        List<String> list = likeMapper.selectArticleIdsByUserId(id);
        List<ArticleInfo> articleInfoList = new ArrayList<>();
        for (int i = 0; i < list.size() ; i++){
            Article article = articleMapper.selectById(list.get(i));
            List<String> tagList = tagMapper.getTagsByArticleId(article.getArticleId());
            articleInfoList.add(articleService.setArticleToArticleInfo(article,tagList));
        }
        return articleInfoList;
    }

    public ArticleInfo likeArticle(String userid, String articleId){
        int i = likeMapper.insert(userid, articleId);
        if (i > 0){;
            Article article = articleMapper.selectById(articleId);
            List<String> tagList = tagMapper.getTagsByArticleId(article.getArticleId());
            return articleService.setArticleToArticleInfo(article, tagList);
        }else{
            return null;
        }
    }

    public void cancelLike(String userid, String articleId){
        likeMapper.delete(userid,articleId);
    }

    public List<ArticleInfo> getCollectList(String id){
        List<String> list = collectMapper.selectArticleIdListByUserId(id);
        List<ArticleInfo> articleInfoList = new ArrayList<>();
        for (int i = 0; i < list.size() ; i++){
            Article article = articleMapper.selectById(list.get(i));
            List<String> tagList = tagMapper.getTagsByArticleId(article.getArticleId());
            articleInfoList.add(articleService.setArticleToArticleInfo(article, tagList));
        }
        return articleInfoList;
    }

    public ArticleInfo collectArticle(String userid, String articleId){
        int i = collectMapper.insert(userid, articleId);
        if (i > 0){;
            Article article = articleMapper.selectById(articleId);
            List<String> tagList = tagMapper.getTagsByArticleId(article.getArticleId());
            return articleService.setArticleToArticleInfo(article, tagList);
        }else{
            return null;
        }
    }

    public void cancelCollect(String userid, String articleId){
        collectMapper.delete(userid,articleId);
    }

}
