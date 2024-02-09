package cn.hanamizu.campushelp.controller;

import cn.hanamizu.campushelp.entity.Task;
import cn.hanamizu.campushelp.entity.User;
import cn.hanamizu.campushelp.service.TaskService;
import cn.hanamizu.campushelp.service.UserService;
import cn.hanamizu.campushelp.utils.tools.MessageUtil;
import cn.hanamizu.campushelp.utils.tools.PocketMoney;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageUtil messageUtil;
    @Autowired
    private PocketMoney money;

    // 获取当前登录user所在学校的任务
    @GetMapping
    public Map<String, Object> tasks(Long id) {
        User user = userService.getById(id);
        if (user != null) {
            QueryWrapper<Task> wrapper = new QueryWrapper<>();
            wrapper.eq("user_school_id", user.getSchool().getId());

            return messageUtil.message(true, "success", "task", taskService.list(wrapper));
        }
        List<Task> tasks = taskService.list();
        return messageUtil.message(true, "success", "task", tasks);
    }

    // 根据id获取task
    @GetMapping("/{id}")
    public Map<String, Object> task(@PathVariable String id) {
        Task task = taskService.getById(id);
        return messageUtil.message(true, "success", "task", task);
    }

    // 当前登录User, 已发布的task
    @GetMapping("/published")
    public Map<String, Object> published(Long id) {
        return messageUtil.message(true, "success", "task", publishAndAcceptMethods(id, "publish_user_id"));
    }

    // 当前登录User, 已接受的task
    @GetMapping("/accepted")
    public Map<String, Object> accepted(Long id) {
        return messageUtil.message(true, "success", "task", publishAndAcceptMethods(id, "accept_user_id"));
    }

    // 获取发布和接受的task
    public List<Task> publishAndAcceptMethods(Long id, String field) {
//         User user = (User) session.getAttribute("user");
        QueryWrapper<Task> wrapper = new QueryWrapper<>();
        wrapper.eq(field, id);
        return taskService.list(wrapper);
    }

    // 发布新task
    @PostMapping
    public Map<String, Object> saveTask(Task task) {
        User user = userService.getById(task.getPublishId());
//        // test 临时数据
//        User user = userService.getById(13);

        if (user.getBalance() >= task.getReward()) {
            boolean save = taskService.save(task);
            if (save) {
                money.transfer("balance=balance-", task.getReward(), user.getStudentId());
            }
            return messageUtil.message(true, "发布成功", "", null);
        } else {
            return messageUtil.message(false, "error", "", null);
        }
    }

    // 发布人取消task
    @DeleteMapping("/{id}")
    public Map<String, Object> delTask(@PathVariable Long id) {
        Task task = taskService.getById(id);
        System.out.println(task);
        if (task != null) {
            taskService.removeById(id);
            money.transfer("balance=balance+", task.getReward(), task.getPublish().getStudentId());
        }
        return messageUtil.message(true, "取消成功", "", null);
    }

    // 接单人取消task
    @PutMapping("/takerCancel/{id}")
    public Map<String, Object> takerCancel(@PathVariable Long id) {

        UpdateWrapper<Task> wrapper = new UpdateWrapper<>();
        wrapper.setSql("accept_user_id=null")
                .setSql("order_time=null")
                .setSql("state=0")
                .eq("id", id);

        boolean update = taskService.update(wrapper);


        if (update) {
            return messageUtil.message(true, "取消成功", "", null);
        }
        return messageUtil.message(false, "failed", "", null);
    }

    // 接单人接受task
    @PutMapping("/takerAccept")
    public Map<String, Object> takerAccept(Long id, Long acceptId) {

        UpdateWrapper<Task> wrapper = new UpdateWrapper<>();
        wrapper.setSql("accept_user_id=" + acceptId)
                .setSql("order_time=now()")
                .setSql("state=1")
                .eq("id", id);
        boolean b = taskService.update(wrapper);

        if (b) {
            return messageUtil.message(true, "接受成功", "", null);
        }
        return messageUtil.message(false, "failed", "", null);
    }

    // 完成task
    @PutMapping("/{id}")
    public Map<String, Object> missionCompleted(@PathVariable Long id) {

        UpdateWrapper<Task> wrapper = new UpdateWrapper<>();
        wrapper.setSql("end_time=now()")
                .setSql("state=2")
                .eq("id", id);
        boolean b = taskService.update(wrapper);

        if (b) {
            Task task = taskService.getById(id);
            if (task != null) {
                money.transfer("balance=balance+", task.getReward(), task.getAccept().getStudentId());
            }
            return messageUtil.message(true, "完成任务", "", null);
        }
        return messageUtil.message(false, "failed", "", null);
    }

    // 修改task
    @PutMapping("/edit")
    public Map<String, Object> editTask(Task task, HttpSession session) {

        User user = (User) session.getAttribute("user");

        Task byId = taskService.getById(task.getId());

        //判断是否为自己发布的任务
        if (user.getId().equals(byId.getPublish().getId())) {

            boolean update = taskService.updateById(task);

            if (update) {
                return messageUtil.message(true, "修改成功", "", null);
            }
            return messageUtil.message(false, "failed", "", null);
        }
        return messageUtil.message(false, "no permission", "", null);
    }

}

