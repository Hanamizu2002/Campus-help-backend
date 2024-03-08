package cn.hanamizu.campushelp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@TableName(resultMap = "productResultMap")
public class Product {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer cost;
    private String image;
    private Integer stock;
    private Integer state;
}
