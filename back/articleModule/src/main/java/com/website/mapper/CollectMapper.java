package com.website.mapper;

import com.website.entity.Collect;
import com.website.entity.Like;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectMapper {
    // 查询
    List<String> selectArticleIdListByUserId(String id);
    Collect selectByArticleIdAndUserId(String articleId, String userId);
    // 增加
    int insert(String userid, String articleid);
    //删除
    int delete(String userid, String articleid);
}
