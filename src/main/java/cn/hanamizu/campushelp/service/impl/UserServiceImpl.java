package cn.hanamizu.campushelp.service.impl;

import cn.hanamizu.campushelp.entity.User;
import cn.hanamizu.campushelp.mapper.UserMapper;
import cn.hanamizu.campushelp.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public Boolean addCoin(Double coin) {

        return null;
    }
}
