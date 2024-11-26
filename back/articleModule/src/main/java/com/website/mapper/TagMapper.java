package com.website.mapper;

import com.website.entity.Tag;

import java.util.List;

public interface TagMapper {
    // 查询
    List<Tag> getAllTags();
    int getIdByName(String name);
}
