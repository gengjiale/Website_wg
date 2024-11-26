package com.website.mapper;

import com.website.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper {
    // 查询
    Article selectById(String id); //
    List<Article> selectByCurrentAndNum(int current, int num); //
    List<Article> selectByWD(String wd, int current, int num);
    List<Article> selectByAuthor(int userid, int current, int num); //
    List<Article> selectByTag(int tag, int current, int num );//

    //增加
    int insert(String id, int authorId, String title, String describe, String content,String time, int tag);//

    //删除
    int delete(int id);
}
