package com.sdjtxy.service;

import com.sdjtxy.pojo.User;

public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    public void registerUser(User user);

    /**
     * 登录
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 判断用户名是否可用
     * @param username
     * @return 返回true表示用户名可用，返回false表示用户名已存在
     */
    public boolean existUsername(String username);

}
