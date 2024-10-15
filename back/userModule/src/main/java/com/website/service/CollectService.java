package com.website.service;

import com.website.dto.ArticleInfo;

import java.util.ArrayList;
import java.util.List;

public class CollectService {
    public List<ArticleInfo> getCollectionList(String userid){
        return new ArrayList<>();
    }
    public ArticleInfo saveArticle(String userid,String articleid){
        return new ArticleInfo();
    }
    public void cancelSave(String userid,String articleid){

    }
}
