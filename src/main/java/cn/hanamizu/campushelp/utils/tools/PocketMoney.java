package cn.hanamizu.campushelp.utils.tools;

import cn.hanamizu.campushelp.entity.User;
import cn.hanamizu.campushelp.service.UserService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PocketMoney {
    @Autowired
    private UserService userService;
    @Autowired
    private MessageUtil messageUtil;

    /**
     * 零钱通用方法
     * @param condition 计算条件
     * @param balance 金额
     * @param studentId 学生id
     * @return 结果
     */
    public Map<String, Object> transfer(String condition, Double balance, String studentId) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.setSql(condition + balance)
                .eq("student_id", studentId);
        boolean update = userService.update(wrapper);
        if (update) {
            return messageUtil.message(true, "success", "", null);
        }
        return messageUtil.message(false, "error", "", null);
    }
}
