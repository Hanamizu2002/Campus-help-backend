package cn.hanamizu.campushelp.service.impl;

import cn.hanamizu.campushelp.entity.Admin;
import cn.hanamizu.campushelp.mapper.AdminMapper;
import cn.hanamizu.campushelp.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean save(Admin entity) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("account", entity.getAccount());

        Admin admin = adminMapper.selectOne(wrapper);
        if (admin == null) {
            adminMapper.insert(entity);
            return true;
        }
        return false;
    }
}
