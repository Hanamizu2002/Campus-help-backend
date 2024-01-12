package cn.hanamizu.campushelp.service.impl;

import cn.hanamizu.campushelp.entity.Class;
import cn.hanamizu.campushelp.mapper.ClassMapper;
import cn.hanamizu.campushelp.service.ClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassService {
}
