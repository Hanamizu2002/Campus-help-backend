package cn.hanamizu.campushelp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(resultMap = "configResultMap")
public class Config {
    private String key;
    private String value;
}