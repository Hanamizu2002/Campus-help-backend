package cn.hanamizu.campushelp.service.impl;

import cn.hanamizu.campushelp.entity.Task;
import cn.hanamizu.campushelp.mapper.TaskMapper;
import cn.hanamizu.campushelp.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {
}
