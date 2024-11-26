package com.website.mapper;

import com.website.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskMapper {
    // 查询
    List<Task> selectTaskByUserId(int id);
    Task selectTaskByUserIdAndTaskId(int userid, String taskid);

    // 更新
    int updateTaskByUserIdAndTaskId(int userid, String taskid, String state);
}
