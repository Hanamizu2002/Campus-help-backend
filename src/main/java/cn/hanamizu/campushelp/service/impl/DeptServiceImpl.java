package cn.hanamizu.campushelp.service.impl;

import cn.hanamizu.campushelp.entity.Dept;
import cn.hanamizu.campushelp.mapper.DeptMapper;
import cn.hanamizu.campushelp.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
}
