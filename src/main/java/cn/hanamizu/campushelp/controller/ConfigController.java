package cn.hanamizu.campushelp.controller;

import cn.hanamizu.campushelp.entity.Config;
import cn.hanamizu.campushelp.service.ConfigService;
import cn.hanamizu.campushelp.utils.tools.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * todo 前后端交互
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @Autowired
    private MessageUtil messageUtil;

    // 获取所有配置项
    @GetMapping()
    public Map<String, Object> listConfigs() {
        List<Config> configs = configService.list();
        return messageUtil.message(true, "获取配置列表成功", "configs", configs);
    }

    // 根据key获取单个配置项
    @GetMapping("/{key}")
    public Map<String, Object> getConfigByKey(@PathVariable String key) {
        Config config = configService.getById(key);
        if (config != null) {
            return messageUtil.message(true, "获取配置成功", "config", config);
        }
        return messageUtil.message(false, "配置不存在", null, null);
    }

    // 修改配置项
    @PutMapping
    public Map<String, Object> updateConfig(Config config) {
        String update = configService.updateByKey(config.getKey(), config.getValue());
        System.out.println(update);
        if (update != null) {
            return messageUtil.message(true, "更新配置成功", null, null);
        }
        return messageUtil.message(false, "更新配置失败", null, null);
    }
}
