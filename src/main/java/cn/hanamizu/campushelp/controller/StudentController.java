package cn.hanamizu.campushelp.controller;

import cn.hanamizu.campushelp.entity.Student;
import cn.hanamizu.campushelp.service.StudentService;
import cn.hanamizu.campushelp.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/student/stuage")
public class StudentController
{
    @Autowired
    private StudentService studentService;

    @Autowired
    private MessageUtil messageUtil;

    /*
      TODO 获取学生管理详细信息
     */

    /*
     * 通过id查找
     */
    @GetMapping("/{id}")
    public Map<String, Object> findSxcStudentById(@PathVariable Long id) {
        Student student = studentService.selectStudentById(id);
        return messageUtil.message(true, "success", "student", student);
    }

    /*
      TODO 新增学生管理
     */


    /*
      TODO 修改学生管理
     */


    /*
      TODO 删除学生管理
     */
}

