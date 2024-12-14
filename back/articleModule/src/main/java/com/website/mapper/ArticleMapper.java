package com.website.mapper;

import com.website.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper {
    // 查询
    Article selectById(String id); //
    List<Article> selectByCurrentAndNum(int current, int num); //
    List<Article> selectFav();
    List<Article> selectByWD(String wd, int current, int num);
    List<Article> selectByAuthor(String userid, int current, int num); //

    //增加
    int insert(String id, String authorId, String title, String describe, String content,String time,String picture);//

    // 更新
    void likeArticle(String articleId);
    void cancelLike(String articleId);
    void collectArticle(String articleId);
    void cancelCollect(String articleId);
    void commentArticle(String articleId);
    void cancelComment(String articleId);

    //删除
    int delete(String id);
}
