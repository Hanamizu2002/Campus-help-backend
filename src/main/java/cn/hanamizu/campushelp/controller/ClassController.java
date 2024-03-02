package cn.hanamizu.campushelp.controller;

import cn.hanamizu.campushelp.entity.Class;
import cn.hanamizu.campushelp.service.ClassService;
import cn.hanamizu.campushelp.utils.tools.MessageUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private ClassService classService;
    @Autowired
    private MessageUtil message;

    // 获取全部class
    @GetMapping
    public Map<String, Object> myClass() {
        List<Class> c = classService.list();
        return message.message(true, "请求成功", "class", c);
    }

    // 根据id获取myClass
    @GetMapping("/{id}")
    public Map<String, Object> myClass(@PathVariable Long id) {
        Class c = classService.getById(id);
        return message.message(true, "请求成功", "class", c);
    }

    // 添加myClass
    @PostMapping
    public Map<String, Object> saveClass(Class c) {
        QueryWrapper<Class> wrapper = new QueryWrapper<>();
        wrapper.eq("school_id", c.getSchoolId())
                .eq("dept_id", c.getDeptId())
                .eq("name", c.getName());

        Class one = classService.getOne(wrapper);
        if (one == null) {
            classService.save(c);
            return message.message(true, "添加班级成功", "", null);
        }

        return message.message(true, "error, 班级已存在", "", null);
    }

    // 删除
    @DeleteMapping("/{id}")
    public Map<String, Object> delClass(@PathVariable Long id) {
        boolean remove = classService.removeById(id);
        if (remove) {
            return message.message(true, "删除成功", "", null);
        }
        return message.message(true, "error, 删除失败", "", null);
    }
}

