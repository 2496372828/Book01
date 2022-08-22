package com.sdjtxy.service.impl;

import com.sdjtxy.dao.UserDao;
import com.sdjtxy.dao.impl.UserDaoImpl;
import com.sdjtxy.pojo.User;
import com.sdjtxy.service.UserService;

public class UserServiceImpl implements UserService {
    //我们此处进行注册登录等等功能涉及到访问数据库，所以涉及到Dao持久层来操作数据库
    private UserDao userDao=new UserDaoImpl();
    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    /**
     *
     * @param user
     * @return 如果返回null，说明未查到该用户，登陆失败。
     */
    @Override
    public User login(User user) {
        return userDao.queryByUsernameAndPassword(user.getUsername(), user.getPassword());

    }

    @Override
    public boolean existUsername(String username) {
        if(userDao.queryByUsername(username)==null){
            return false;
        }else{
            return true;
        }
    }
}
