package cn.hanamizu.campushelp.service;

import cn.hanamizu.campushelp.entity.Config;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ConfigService extends IService<Config> {
    String getValueByKey(String key);
}
