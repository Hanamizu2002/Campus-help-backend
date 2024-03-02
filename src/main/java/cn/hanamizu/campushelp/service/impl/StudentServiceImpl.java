package cn.hanamizu.campushelp.service.impl;

import cn.hanamizu.campushelp.entity.Student;
import cn.hanamizu.campushelp.mapper.StudentMapper;
import cn.hanamizu.campushelp.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    /**
     * 通过id查询
     *
     * @param id 学生管理主键
     * @return 查询到的学生管理实体
     */
    @Override
    public Student selectStudentById(Long id) {
        return studentMapper.selectStudentById(id);
    }

    /**
     * TODO 查询列表
     *
     * @param student 学生管理
     * @return 学生管理列表
     */
    @Override
    public List<Student> selectStudentList(Student student) {
        return studentMapper.selectStudentList(student);
    }

    /**
     * TODO 添加学生管理
     *
     * @param student 学生管理
     * @return 结果
     */
    @Override
    public boolean insertStudent(Student student) {
        return studentMapper.insertStudent(student);
    }

    /**
     * TODO 修改学生管理
     *
     * @param student 学生管理
     * @return 结果
     */
    @Override
    public boolean updateStudent(Student student) {
        return updateStudent(student);
    }

    /**
     * TODO 批量删除学生管理
     *
     * @param ids 需要删除的学生管理主键集合
     * @return 结果
     */
    @Override
    public boolean deleteStudentByIds(Long[] ids) {
        return studentMapper.deleteStudentByIds(ids);
    }

    /**
     * TODO 通过id删除学生管理
     *
     * @param id 学生管理主键
     * @return 结果
     */
    @Override
    public boolean deleteSxcStudentById(Long id) {
        return studentMapper.deleteStudentById(id);
    }
}
