package com.sdjtxy.dao;
/**
 * 在此接口中测试以下三个方法：
 * ctrl+shift+t：
 */

import com.sdjtxy.pojo.User;

public interface UserDao {

    /**
     * 通过用户名查询来返回User对象
     * @param username
     * @return  如果返回null，则说明不存在输入用户信息
     */
    public User queryByUsername(String username);

    /**
     * 通过用户名和密码来查询
     * @param username
     * @param password
     * @return 如果返回null，说明用户名或密码输入错误
     */
    public User queryByUsernameAndPassword(String username,String password);

    /**
     * 保存用户信息
     * @param user
     * @return 返回-1表示操作失败，返回其他是sql影响的行数
     */
    public int saveUser(User user);
}
