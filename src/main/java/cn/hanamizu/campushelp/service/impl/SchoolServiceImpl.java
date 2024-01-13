package cn.hanamizu.campushelp.service.impl;

import cn.hanamizu.campushelp.entity.School;
import cn.hanamizu.campushelp.mapper.SchoolMapper;
import cn.hanamizu.campushelp.service.SchoolService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements SchoolService {
}
