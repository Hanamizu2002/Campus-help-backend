package cn.hanamizu.campushelp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Data
@TableName(resultMap = "exchangeRecordResultMap")
public class ExchangeRecord {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long productId;
    private String address;
    private String name;
    private String phone;
    private LocalDateTime exchangeTime;
    private Integer state;
    @TableField(exist = false)
    private Product product;
}
