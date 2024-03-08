package cn.hanamizu.campushelp.controller;

import cn.hanamizu.campushelp.entity.Dept;
import cn.hanamizu.campushelp.entity.School;
import cn.hanamizu.campushelp.entity.TaskType;
import cn.hanamizu.campushelp.service.DeptService;
import cn.hanamizu.campushelp.utils.tools.MessageUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;
    @Autowired
    private MessageUtil message;

    // 获取全部dept
    @GetMapping
    public Map<String, Object> depts() {
        QueryWrapper<Dept> wrapper = new QueryWrapper<>();
        wrapper.ne("state", 0);
        return message.message(true, "请求成功", "school", deptService.list(wrapper));
    }

    @GetMapping("/school/{id}")
    public Map<String, Object> deptsBySchool(@PathVariable Long id) {
        QueryWrapper<Dept> wrapper = new QueryWrapper<>();
        wrapper.eq("school_id", id);
        return message.message(true, "请求成功", "dept", deptService.list(wrapper));
    }

    // 根据id获取dept
    @GetMapping("/{id}")
    public Map<String, Object> dept(@PathVariable Long id) {
        Dept dept = deptService.getById(id);
        return message.message(true, "请求成功", "dept", dept);
    }

    // 添加dept
    @PostMapping
    public Map<String, Object> saveDept(Dept dept) {
        QueryWrapper<Dept> wrapper = new QueryWrapper<>();
        wrapper.eq("school_id", dept.getSchoolId())
                .eq("name", dept.getName());

        Dept one = deptService.getOne(wrapper);
        if (one == null) {
            deptService.save(dept);
            return message.message(true, "添加系别成功", "", null);
        }

        return message.message(true, "error, 该系已存在", "", null);
    }

    // 删除
    @DeleteMapping("/{id}")
    public Map<String, Object> delDept(@PathVariable Long id) {
        UpdateWrapper<Dept> wrapper = new UpdateWrapper<>();
        wrapper.setSql("state=0")
                .eq("id", id);
        if (deptService.update(wrapper)) {
            return message.message(true, "删除成功", "", null);
        }
        return message.message(true, "error, 删除失败", "", null);
    }
}
