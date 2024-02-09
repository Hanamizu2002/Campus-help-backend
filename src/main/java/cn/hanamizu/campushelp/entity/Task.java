package cn.hanamizu.campushelp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName(resultMap = "taskResultMap")
public class Task {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField(value = "publish_user_id")
    private Long publishId;
    @TableField(value = "accept_user_id")
    private Long acceptId;
    @TableField(value = "user_school_id")
    private Long schoolId;
    private Double reward;
    private Date createTime;
    private Date orderTime;
    private Date endTime;
    private String taskTitle;
    private String taskContext;
    private Integer state;
    //todo 类型，任务发布审核

    @TableField(exist = false)
    private User publish;
    @TableField(exist = false)
    private User accept;
    @TableField(exist = false)
    private School school;
}
