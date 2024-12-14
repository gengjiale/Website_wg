package com.website.mapper;

import com.website.entity.Like;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeMapper {
    // 查询
    List<String> selectArticleIdsByUserId(String userId);
    Like selectByArticleIdAndUserId(String articleId, String userId);
    // 增加
    int insert(String userId, String articleId);
    //删除
    int delete(String userId, String articleId);
}
