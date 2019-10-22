package com.damon.fsd.service;

import com.damon.fsd.entity.SysUser;

public interface UserService  {

     SysUser loadUserByUsername(String username);

    void updateUser(SysUser sysUser);

    void register(SysUser sysUser);
}
