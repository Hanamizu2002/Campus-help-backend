package cn.hanamizu.campushelp.controller;

import cn.hanamizu.campushelp.entity.School;
import cn.hanamizu.campushelp.service.SchoolService;
import cn.hanamizu.campushelp.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private MessageUtil messageUtil;

    // 获取全部school
    @GetMapping
    public Map<String, Object> schools() {
        List<School> schools = schoolService.list();
        return messageUtil.message(true, "success", "school", schools);
    }

}
