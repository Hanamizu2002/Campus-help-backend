package cn.hanamizu.campushelp.controller;

import cn.hanamizu.campushelp.entity.Product;
import cn.hanamizu.campushelp.service.ProductService;
import cn.hanamizu.campushelp.utils.tools.MessageUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private MessageUtil message;

    // 获取全部Product
    @GetMapping
    public Map<String, Object> products() {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.ne("state", 0);
        return message.message(true, "请求成功", "product", productService.list(wrapper));

    }

    // 根据id获取Product
    @GetMapping("/{id}")
    public Map<String, Object> findProductById(@PathVariable Integer id) {
        Product product = productService.getById(id);
        if (product != null) {
            return message.message(true, "请求成功", "product", product);
        }
        return message.message(false, "未找到指定的Product", "", null);
    }

    // 添加Product
    @PostMapping
    public Map<String, Object> saveProduct(Product product) {
        boolean save = productService.save(product);
        if (save) {
            return message.message(true, "添加成功", "", null);
        }
        return message.message(false, "添加失败", "", null);
    }

    // 删除Product
    @DeleteMapping("/{id}")
    public Map<String, Object> delProduct(@PathVariable Integer id) {
        UpdateWrapper<Product> wrapper = new UpdateWrapper<>();
        wrapper.setSql("state=0")
                .eq("id", id);
        if (productService.update(wrapper)) {
            return message.message(true, "删除成功", "", null);
        }
        return message.message(false, "删除失败", "", null);
    }

    // 更新Product
    @PutMapping
    public Map<String, Object> putProduct(Product product) {
        boolean update = productService.updateById(product);
        if (update) {
            return message.message(true, "更新成功", "", null);
        }
        return message.message(false, "更新失败", "", null);
    }
}