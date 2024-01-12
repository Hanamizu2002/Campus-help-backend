package cn.hanamizu.campushelp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@ToString
@Data
@TableName(resultMap = "userResultMap")
@Alias("User")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String studentId;
    private String password;
    private String phone;
    private Long schoolId;
    private Long deptId;
    private Long classId;
    private Integer sex;
    private String username;
    private Date createTime;
    private Double balance;
    private Integer state;

    @TableField(exist = false)
    private School school;
    @TableField(exist = false)
    private Dept dept;
    @TableField(exist = false)
    private Class aClass;
}
