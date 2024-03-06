package cn.hanamizu.campushelp.service.impl;

import cn.hanamizu.campushelp.entity.Product;
import cn.hanamizu.campushelp.mapper.ProductMapper;
import cn.hanamizu.campushelp.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
