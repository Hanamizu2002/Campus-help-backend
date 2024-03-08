package cn.hanamizu.campushelp.service;

import cn.hanamizu.campushelp.entity.TaskType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TaskTypeService extends IService<TaskType> {
    List<TaskType> listActiveTaskTypes();
}
