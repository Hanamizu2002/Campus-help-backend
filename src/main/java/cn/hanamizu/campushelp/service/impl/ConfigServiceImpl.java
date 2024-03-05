package cn.hanamizu.campushelp.service.impl;

import cn.hanamizu.campushelp.entity.Config;
import cn.hanamizu.campushelp.mapper.ConfigMapper;
import cn.hanamizu.campushelp.service.ConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {
    @Resource
    private ConfigMapper configMapper;
    @Override
    public String getValueByKey(String key) {
        return configMapper.getValueByKey(key);
    }

    @Override
    public String updateByKey(String key, String value) {
        return configMapper.updateByKey(key, value);
    }

}
