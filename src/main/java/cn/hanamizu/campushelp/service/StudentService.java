package cn.hanamizu.campushelp.service;

import cn.hanamizu.campushelp.entity.Student;

import java.util.List;

public interface StudentService {
    /**
     * 查询学生管理
     *
     * @param id 学生管理主键
     * @return 学生管理
     */
    Student selectStudentById(Long id);

    /**
     * 查询学生管理列表
     *
     * @param student 学生管理
     * @return 学生管理集合
     */
    List<Student> selectStudentList(Student student);

    /**
     * 新增学生管理
     *
     * @param student 学生管理
     * @return 结果
     */
    boolean insertStudent(Student student);

    /**
     * 修改学生管理
     *
     * @param student 学生管理
     * @return 结果
     */
    boolean updateStudent(Student student);

    /**
     * 批量删除学生管理
     *
     * @param ids 需要删除的学生管理主键集合
     * @return 结果
     */
    boolean deleteStudentByIds(Long[] ids);

    /**
     * 删除学生管理信息
     *
     * @param id 学生管理主键
     * @return 结果
     */
    boolean deleteSxcStudentById(Long id);
}
