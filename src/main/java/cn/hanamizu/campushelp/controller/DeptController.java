package cn.hanamizu.campushelp.controller;

import cn.hanamizu.campushelp.entity.Dept;
import cn.hanamizu.campushelp.service.DeptService;
import cn.hanamizu.campushelp.utils.tools.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;
    @Autowired
    private MessageUtil messageUtil;

    @GetMapping("/getDepts")
    public Map<String, Object> depts() {
        List<Dept> depts = deptService.list();
        return messageUtil.message(true, "success", "dept", depts);
    }
}
