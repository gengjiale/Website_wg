package com.website.service;

import com.website.dto.TaskInfo;
import com.website.entity.Task;
import com.website.mapper.TaskMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    TaskMapper taskMapper;

    /**
     * 获得用户的任务列表
     * @param id
     * @return
     */
    public List<TaskInfo> getAllTask(int id){
        ArrayList<TaskInfo> taskInfoArrayList = new ArrayList<>();
        List<Task> taskList = taskMapper.selectTaskByUserId(id);
        for(int i = 0 ; i < taskList.size(); i++){
            TaskInfo taskInfo = new TaskInfo();
            taskInfoArrayList.add(taskInfo);
        }
        return taskInfoArrayList;
    }

    /**
     * 完成任务
     * 根据用户id和任务id找到任务
     * 修改对应任务的状态为完成
     * 返回任务信息
     * @param userid
     * @param taskid
     * @return
     */
    public TaskInfo completeTask(int userid, String taskid){
        int i = taskMapper.updateTaskByUserIdAndTaskId(userid, taskid, "已完成");
        if ( i > 0){
            TaskInfo taskInfo = new TaskInfo();
            Task task = taskMapper.selectTaskByUserIdAndTaskId(userid, taskid);
            taskInfo.setId(task.getId());
            return taskInfo;
        }else {
            return null;
        }
    }
}
