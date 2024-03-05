package cn.hanamizu.campushelp.mapper;

import cn.hanamizu.campushelp.entity.Config;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface ConfigMapper extends BaseMapper<Config> {
    String getValueByKey(String key);

    String updateByKey(String key, String value);
}
