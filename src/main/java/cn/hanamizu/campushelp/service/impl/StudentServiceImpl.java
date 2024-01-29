package cn.hanamizu.campushelp.service.impl;

import cn.hanamizu.campushelp.entity.Student;
import cn.hanamizu.campushelp.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    /**
     * TODO 通过id查询
     * @param id 学生管理主键
     * @return 查询到的学生管理实体
     */
    @Override
    public Student selectStudentById(Long id) {
        //todo mapper
        return null;
    }

    /**
     * TODO 查询列表
     * @param student 学生管理
     * @return 学生管理列表
     */
    @Override
    public List<Student> selectStudentList(Student student) {
        return null;
    }

    /**
     * TODO 添加学生管理
     * @param student 学生管理
     * @return
     */
    @Override
    public int insertStudent(Student student) {
        return 0;
    }

    /**
     * TODO 修改学生管理
     * @param student 学生管理
     * @return
     */
    @Override
    public int updateStudent(Student student) {
        return 0;
    }

    /**
     * TODO 批量删除学生管理
     * @param ids 需要删除的学生管理主键集合
     * @return
     */
    @Override
    public int deleteStudentByIds(Long[] ids) {
        return 0;
    }

    /**
     * TODO 通过id删除学生管理
     * @param id 学生管理主键
     * @return
     */
    @Override
    public int deleteSxcStudentById(Long id) {
        return 0;
    }
}
