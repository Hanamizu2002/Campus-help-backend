package cn.hanamizu.campushelp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 评论实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class Remark extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /**
     * 评价id
     */
    private Long id;

    /**
     * 星级
     */
    private Long star;

    /**
     * 任务id
     */
    private Long taskId;

    /**
     * 送货人id
     */
    private Long acceptId;

    /**
     * 评价人id
     */
    private Long publishId;

    @TableField(exist = false)
    private User publish;
}
