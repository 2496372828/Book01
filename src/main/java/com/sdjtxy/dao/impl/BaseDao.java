package com.sdjtxy.dao.impl;

import com.sdjtxy.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *  关于下边返回值类型<T> T
 *  这个<T> T 可以传入任何类型的List
 *       参数T
 *           第一个 表示是泛型
 *           第二个 表示返回的是T类型的数据
 *           第三个 限制参数类型为T
 *
 */


public abstract class BaseDao {
    private QueryRunner runner=new QueryRunner();

    /**
     *
     * @param sql   sql语句
     * @param args  占位符元素
     * @return 返回进行sql增删改语句之后执行的条数
     */
    public int update(String sql,Object...args){
        Connection conn=JdbcUtils.getConnection();
        try {
            return runner.update(conn,sql,args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }


    }

    /**
     * 查询返回一个Javabean的sql语句
     * @param type 返回的对象类型
     * @param sql   执行的sql语句
     * @param args  sql对应的参数
     * @param <T>   返回的类型的泛型
     * @return
     */
    public <T> T queryForOne(Class<T> type,String sql,Object...args){
        Connection conn=JdbcUtils.getConnection();
        try {
            return runner.query(conn,sql,new BeanHandler<T>(type),args);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }

    }

    /**
     *查询返回多个javabean的SQL语句
     * @param type
     * @param sql
     * @param args
     * @param <T>  返回查询集合
     * @return
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object...args){
        Connection conn=JdbcUtils.getConnection();
        try {
            List<T> list=runner.query(conn,sql,new BeanListHandler<T>(type),args);
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }

    }

    /**
     * 查询一行一列的sql语句
     * @param sql
     * @param args
     * @return 特殊查询值 例如数量类型int 等等
     */
    public Object queryForSingleValues(String sql,Object...args){
        Connection conn=JdbcUtils.getConnection();
        try {
            return runner.query(conn,sql,new ScalarHandler(),args);
        } catch (SQLException throwables){
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }

    }

}
