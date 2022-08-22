package com.sdjtxy.test;

import com.sdjtxy.pojo.User;
import com.sdjtxy.service.UserService;
import com.sdjtxy.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService userService=new UserServiceImpl();
    @Test
    public void registerUser() {

        userService.registerUser(new User(null,"admin","201611","admin@126.com"));
    }

    @Test
    public void login() {

        System.out.println( userService.login(new User(null,"admin","201611","admin@126.com")));
    }

    @Test
    public void existUsername() {
        if(userService.existUsername("admin")){
            System.out.println("用户名存在");
        }else {
            System.out.println("用户名不存在");
        }
    }
}