package cn.hanamizu.campushelp.controller;

import cn.hanamizu.campushelp.entity.User;
import cn.hanamizu.campushelp.service.UserService;
import cn.hanamizu.campushelp.utils.MessageUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private MessageUtil messageUtil;
    @Autowired
    private UserService userService;

    //TODO 登录

    //获取user列表
    @GetMapping("/getUsers")
    public Map<String, Object> getUsers(String studentId, String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (studentId != null) {
            wrapper.eq("student_id", studentId);
            return messageUtil.message(true, "success", "user", userService.list(wrapper));
        }
        if (username != null) {
            wrapper.like("username", username);
            return messageUtil.message(true, "success", "user", userService.list(wrapper));
        }
        List<User> users = userService.list();
        return messageUtil.message(true, "success", "user", users);
    }

    @GetMapping("/{id}")
    public Map<String, Object> user(@PathVariable String id) {
        User user = userService.getById(id);
        return messageUtil.message(true, "success", "user", user);
    }

    //更新user信息

    //添加user

    //删除
}
