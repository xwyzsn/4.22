package com.example.backend_422.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend_422.common.Result;
import com.example.backend_422.entity.User;
import com.example.backend_422.mapper.UserMapper;
import com.example.backend_422.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xwyzsn
 * @since 2023-04-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    private UserMapper userMapper;

    public Result getAll() {
        return Result.succ(userMapper.selectList(null));
    }

    public Result login(String username, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        User user = userMapper.selectOne(wrapper);
        if(user == null){
            return Result.fail("用户不存在");
        }
        if(!user.getPassword().equals(password.trim())){
            return Result.fail("密码错误");
        }
        return Result.succ(user);
    }
}
