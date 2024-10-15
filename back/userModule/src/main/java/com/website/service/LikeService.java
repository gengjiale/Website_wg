package com.website.service;

import com.website.dto.ArticleInfo;

import java.util.ArrayList;
import java.util.List;

public class LikeService {
    public List<ArticleInfo> getLikeList(String userid){
        return new ArrayList<>();
    }
    public ArticleInfo likeArticle(String userid,String articleid){
        return new ArticleInfo();
    }
    public void cancelLike(String userid,String articleid){

    }
}
