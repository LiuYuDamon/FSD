package com.damon.fsd.service.impl;

import com.damon.fsd.entity.SysUser;
import com.damon.fsd.mapper.UserMapper;
import com.damon.fsd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public SysUser loadUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void updateUser(SysUser sysUser) {
        userMapper.update(sysUser);
    }

    @Override
    public void register(SysUser sysUser) {
        sysUser.setRole("ROLE_user");
        userMapper.insert(sysUser);
    }

}
