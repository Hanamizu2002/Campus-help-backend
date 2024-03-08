package cn.hanamizu.campushelp.controller;

import cn.hanamizu.campushelp.entity.ExchangeRecord;
import cn.hanamizu.campushelp.entity.Product;
import cn.hanamizu.campushelp.entity.User;
import cn.hanamizu.campushelp.service.ExchangeRecordService;
import cn.hanamizu.campushelp.service.ProductService;
import cn.hanamizu.campushelp.service.UserService;
import cn.hanamizu.campushelp.utils.tools.MessageUtil;
import cn.hanamizu.campushelp.utils.tools.PocketMoney;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/exchange")
public class ExchangeRecordController {
    @Autowired
    private ExchangeRecordService exchangeRecordService;
    @Autowired
    private MessageUtil message;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PocketMoney money;

    // 获取全部ExchangeRecord
    @GetMapping
    public Map<String, Object> exchangeRecords() {
        List<ExchangeRecord> exchangeRecords = exchangeRecordService.list();
        return message.message(true, "请求成功", "exchangeRecord", exchangeRecords);
    }

    // 根据id获取ExchangeRecord
    @GetMapping("/{id}")
    public Map<String, Object> findExchangeRecordById(@PathVariable Integer id) {
        ExchangeRecord exchangeRecord = exchangeRecordService.getById(id);
        if (exchangeRecord != null) {
            return message.message(true, "请求成功", "exchangeRecord", exchangeRecord);
        }
        return message.message(false, "未找到指定的ExchangeRecord", "", null);
    }

    // 添加ExchangeRecord
    @PostMapping
    public Map<String, Object> saveExchangeRecord(ExchangeRecord exchangeRecord) {
        User user = userService.getById(exchangeRecord.getUserId());
        Product product = productService.getById(exchangeRecord.getProductId());
        if (product.getStock() <= 0)
            return message.message(false, "库存不足", "", null);
        if (user.getCoin() <= product.getCost())
            return message.message(false, "积分不足", "", null);
        boolean save = exchangeRecordService.save(exchangeRecord);
        if (save) {
            money.transfer("coin=coin-", Double.valueOf(product.getCost()), user.getStudentId());
            product.setStock(product.getStock() - 1);
            productService.updateById(product);
            return message.message(true, "添加成功", "", null);
        }
        return message.message(false, "添加失败", "", null);
    }

    // 删除ExchangeRecord
    @DeleteMapping("/{id}")
    public Map<String, Object> delExchangeRecord(@PathVariable Integer id) {
        boolean remove = exchangeRecordService.removeById(id);
        if (remove) {
            return message.message(true, "删除成功", "", null);
        }
        return message.message(false, "删除失败", "", null);
    }

    // 更新ExchangeRecord
    @PutMapping
    public Map<String, Object> putExchangeRecord(ExchangeRecord exchangeRecord) {
        boolean update = exchangeRecordService.updateById(exchangeRecord);
        if (update) {
            return message.message(true, "更新成功", "", null);
        }
        return message.message(false, "更新失败", "", null);
    }

    @PutMapping("/ship")
    public Map<String, Object> putExchangeRecordState(ExchangeRecord exchangeRecord) {
        UpdateWrapper<ExchangeRecord> wrapper = new UpdateWrapper<>();
        wrapper.setSql("state=1")
                .eq("id", exchangeRecord.getId());
        boolean b = exchangeRecordService.update(wrapper);

        if (b) {
            return message.message(true, "已发货", "", null);
        }
        return message.message(false, "failed", "", null);
    }

}
