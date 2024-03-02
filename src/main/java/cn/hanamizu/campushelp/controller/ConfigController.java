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

    // 新增配置项
    @PostMapping("/add")
    public Map<String, Object> addConfig(Config config) {
        boolean save = configService.save(config);
        if (save) {
            return messageUtil.message(true, "新增配置成功", null, null);
        }
        return messageUtil.message(false, "新增配置失败", null, null);
    }

    // 修改配置项
    @PostMapping("/update")
    public Map<String, Object> updateConfig(Config config) {
        boolean update = configService.updateById(config);
        if (update) {
            return messageUtil.message(true, "更新配置成功", null, null);
        }
        return messageUtil.message(false, "更新配置失败", null, null);
    }

    // 删除配置项
    @DeleteMapping("/{key}")
    public Map<String, Object> deleteConfig(@PathVariable String key) {
        boolean remove = configService.removeById(key);
        if (remove) {
            return messageUtil.message(true, "删除配置成功", null, null);
        }
        return messageUtil.message(false, "删除配置失败", null, null);
    }
}
