package com.website.mapper;

import com.website.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {
    // 查询
    List<Category> getAll();
}
