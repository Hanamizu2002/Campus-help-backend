package cn.hanamizu.campushelp.controller;

import cn.hanamizu.campushelp.entity.School;
import cn.hanamizu.campushelp.service.SchoolService;
import cn.hanamizu.campushelp.utils.tools.MessageUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private MessageUtil message;

    // 获取全部school
    @GetMapping
    public Map<String, Object> schools() {
        QueryWrapper<School> wrapper = new QueryWrapper<>();
        wrapper.ne("state", 0);
        return message.message(true, "请求成功", "school", schoolService.list(wrapper));
    }

    // 根据id获取school
    @GetMapping("/{id}")
    public Map<String, Object> findSchoolsById(@PathVariable String id) {
        School school = schoolService.getById(id);
        return message.message(true, "请求成功", "school", school);
    }

    // 添加school
    @PostMapping
    public Map<String, Object> saveSchool(School school) {
        boolean save = schoolService.save(school);
        if (save) {
            return message.message(true, "添加学校成功", "", null);
        }
        return message.message(false, "error,学校已存在", "", null);
    }


    // 删除school
    @DeleteMapping("/{id}")
    public Map<String, Object> delSchool(@PathVariable Long id) {
        UpdateWrapper<School> wrapper = new UpdateWrapper<>();
        wrapper.setSql("state=0")
                .eq("id", id);
        if (schoolService.update(wrapper)) {
            return message.message(true, "删除学校成功", "", null);
        }
        return message.message(false, "error,删除学校失败", "", null);
    }

    // 更新school
    @PutMapping
    public Map<String, Object> putSchool(School school) {
        boolean update = schoolService.updateById(school);
        if (update) {
            return message.message(true, "更新学校信息成功", "", null);
        }
        return message.message(false, "error,更新学校信息失败", "", null);
    }
}
