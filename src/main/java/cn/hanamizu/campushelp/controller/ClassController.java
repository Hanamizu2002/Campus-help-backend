package cn.hanamizu.campushelp.controller;

import cn.hanamizu.campushelp.entity.Class;
import cn.hanamizu.campushelp.service.ClassService;
import cn.hanamizu.campushelp.utils.tools.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private ClassService classService;
    @Autowired
    private MessageUtil messageUtil;

    @GetMapping("/getClasses")
    public Map<String, Object> getClasses() {
        List<Class> c = classService.list();
        return messageUtil.message(true, "请求成功", "class", c);
    }
}
