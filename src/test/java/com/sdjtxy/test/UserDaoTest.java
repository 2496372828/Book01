package com.sdjtxy.test;

import com.sdjtxy.dao.UserDao;
import com.sdjtxy.dao.impl.UserDaoImpl;
import com.sdjtxy.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Test
    public void queryByUsername() {
        UserDao userDao=new UserDaoImpl();
        //正确输出username=2496372828
        if(userDao.queryByUsername("2496372828")==null){
            System.out.println("用户名可用");
        }else{
            System.out.println("用户名已存在");
        }

    }

    @Test
    public void queryByUsernameAndPassword() {
        UserDao userDao=new UserDaoImpl();
        if(userDao.queryByUsernameAndPassword("lb2496","201611zlb")==null){
            System.out.println("用户名或密码输入错误，验证失败！");
        }else{
            System.out.println("输入正确！");
        }


    }

    @Test
    public void saveUser() {
        UserDao userDao=new UserDaoImpl();
        User user=new User(null,"3332971810","201611zlb","3332971810@qq.com");
        if(userDao.saveUser(user)==-1){
            System.out.println("创建新用户失败");
        }

    }
}