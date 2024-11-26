package com.website.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectMapper {
    // 查询
    List<String> selectArticleIdListByUserId(int id); //
    // 增加
    int insert(int userid, String articleid);
    //删除
    int delete(int userid, String articleid);
}
