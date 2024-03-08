package cn.hanamizu.campushelp.service.impl;

import cn.hanamizu.campushelp.entity.TaskType;
import cn.hanamizu.campushelp.mapper.TaskTypeMapper;
import cn.hanamizu.campushelp.service.TaskTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskTypeServiceImpl extends ServiceImpl<TaskTypeMapper, TaskType> implements TaskTypeService {
    @Autowired
    private TaskTypeMapper taskTypeMapper;

    @Override
    public List<TaskType> listActiveTaskTypes() {
        return taskTypeMapper.listActiveTaskTypes();
    }
}
