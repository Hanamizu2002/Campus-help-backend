package cn.hanamizu.campushelp.controller;

import cn.hanamizu.campushelp.entity.User;
import cn.hanamizu.campushelp.service.UserService;
import cn.hanamizu.campushelp.utils.MessageUtil;
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
    private MessageUtil messageUtil;
    @Autowired
    private UserService userService;

    // 登录检查
    @GetMapping("/login")
    public Map<String, Object> checkUserLogin(User checkUser, HttpSession session) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("student_id", checkUser.getStudentId())
                .eq("password", checkUser.getPassword())
                .eq("school_id", checkUser.getSchoolId());
        User user = userService.getOne(wrapper);

        if (user != null) {
            session.setAttribute("user", user);
            return messageUtil.message(true, "success", "user", user);
        }
        return messageUtil.message(false, "login failed");
    }

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
//        return messageUtil.message(false, "error");
    }

    @GetMapping("/{id}")
    public Map<String, Object> user(@PathVariable String id) {
        User user = userService.getById(id);
        return messageUtil.message(true, "success", "user", user);
    }

    //更新user信息
    @PutMapping("/update")
    public Map<String, Object> putUser(User user) {
        boolean update = userService.updateById(user);
        if (update) {
            return messageUtil.message(true, "success", "", null);
        }
        return messageUtil.message(false, "update failed", "", null);
    }

    //添加user
    @PostMapping("/add")
    public Map<String, Object> saveUser(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("school_id", user.getSchoolId())
                .eq("student_id", user.getStudentId());
        User one = userService.getOne(wrapper);
        if (one == null) {
            userService.save(user);
            return messageUtil.message(true, "success", "", null);
        }
        return messageUtil.message(false, "add failed", "", null);
    }

    //删除
    @DeleteMapping("/delete")
    public Map<String, Object> delUser(@PathVariable Long id) {
        boolean remove = userService.removeById(id);
        if (remove) {
            return messageUtil.message(true, "success", "", null);
        }
        return messageUtil.message(false, "delete failed", "", null);
    }

}
