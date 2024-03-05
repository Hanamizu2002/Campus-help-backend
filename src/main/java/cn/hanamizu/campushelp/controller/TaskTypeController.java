package cn.hanamizu.campushelp.controller;

import cn.hanamizu.campushelp.entity.TaskType;
import cn.hanamizu.campushelp.service.TaskTypeService;
import cn.hanamizu.campushelp.utils.tools.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/taskType")
@Slf4j
public class TaskTypeController {
    @Autowired
    private TaskTypeService taskTypeService;
    @Autowired
    private MessageUtil message;

    // 获取全部TaskType
    @GetMapping
    public Map<String, Object> taskTypes() {
        List<TaskType> taskTypes = taskTypeService.list();
        return message.message(true, "请求成功", "taskType", taskTypes);
    }

    // 根据id获取TaskType
    @GetMapping("/{id}")
    public Map<String, Object> findTaskTypeById(@PathVariable Integer id) {
        TaskType taskType = taskTypeService.getById(id);
        if (taskType != null) {
            return message.message(true, "请求成功", "taskType", taskType);
        }
        return message.message(false, "未找到指定的任务类型", "", null);
    }

    // 添加TaskType
    @PostMapping
    public Map<String, Object> saveTaskType(TaskType taskType) {
        boolean save = taskTypeService.save(taskType);
        if (save) {
            return message.message(true, "添加任务类型成功", "", null);
        }
        return message.message(false, "添加任务类型失败", "", null);
    }

    // 删除TaskType
    @DeleteMapping("/{id}")
    public Map<String, Object> delTaskType(@PathVariable Integer id) {
        boolean remove = taskTypeService.removeById(id);
        if (remove) {
            return message.message(true, "删除任务类型成功", "", null);
        }
        return message.message(false, "删除任务类型失败", "", null);
    }

    // 更新TaskType
    @PutMapping
    public Map<String, Object> putTaskType(TaskType taskType) {
        boolean update = taskTypeService.updateById(taskType);
        if (update) {
            return message.message(true, "更新任务类型成功", "", null);
        }
        return message.message(false, "更新任务类型失败", "", null);
    }
}
