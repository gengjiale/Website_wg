package com.website.mapper;

import com.website.entity.Tag;

import java.util.List;

public interface TagMapper {
    //查询
    List<String> getArticleIdsByTag(String tag);
    List<String> getArticleIdsByTagBlur(String tag);
    List<String> getTagsByArticleId(String articleId);
    //增加
    int insert(String articleId, String tag);
    //删除
    int deleteByArticleId(String articleId);

}
