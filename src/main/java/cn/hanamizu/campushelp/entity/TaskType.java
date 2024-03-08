package cn.hanamizu.campushelp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName(resultMap = "taskTypeResultMap")
public class TaskType {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    // 0删除-1正常
    private Integer state;
}
