package cn.hanamizu.campushelp.controller;

import cn.hanamizu.campushelp.entity.User;
import cn.hanamizu.campushelp.service.UserService;
import cn.hanamizu.campushelp.utils.MessageUtil;
import cn.hanamizu.campushelp.utils.PocketMoney;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MessageUtil messageUtil;
    @Autowired
    private PocketMoney money;

    // 检查登录
    @PostMapping("/login")
    public Map<String, Object> checkUserLogin(User checkUser, HttpSession session) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("student_id", checkUser.getStudentId())
                .eq("password", checkUser.getPassword())
                .eq("school_id", checkUser.getSchoolId());
        User user = userService.getOne(wrapper);

        if (user != null) {
            session.setAttribute("user", user);
            return messageUtil.message(true, "登录成功", "user", user);
        }
        return messageUtil.message(false, "login failed", "", null);
    }

    // 获取全部User
    @GetMapping("/getUsers")
    public Map<String, Object> users(String studentId, String username) {
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

    // 根据id获取User
    @GetMapping("/{id}")
    public Map<String, Object> user(@PathVariable String id) {
        User user = userService.getById(id);
        return messageUtil.message(true, "success", "user", user);
    }

    // 添加User
    @PostMapping
    public Map<String, Object> saveUser(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("school_id", user.getSchoolId())
                .eq("student_id", user.getStudentId());
        User one = userService.getOne(wrapper);
        if (one == null) {
            userService.save(user);
            return messageUtil.message(true, "add success", "", null);
        }
        return messageUtil.message(false, "update failed", "", null);
    }

    // 更新信息
    @PutMapping
    public Map<String, Object> putUser(User user) {
        boolean update = userService.updateById(user);
        if (update) {
            return messageUtil.message(true, "update success", "", null);
        }
        return messageUtil.message(false, "error", "", null);
    }

    // 删除学生
    @DeleteMapping("/{id}")
    public Map<String, Object> delUser(@PathVariable Long id) {
        boolean remove = userService.removeById(id);
        if (remove) {
            return messageUtil.message(true, "remove success", "", null);
        }
        return messageUtil.message(false, "error", "", null);
    }

    // 零钱转入
    @PutMapping("rollIn")
    public Map<String, Object> rollIn(String studentId, Double balance) {
        return money.transfer("balance=balance+", balance, studentId);
    }

    // 零钱转出
    @PutMapping("rollOut")
    public Map<String, Object> rollOut(String studentId, Double balance) {
        return money.transfer("balance=balance-", balance, studentId);
    }
}
