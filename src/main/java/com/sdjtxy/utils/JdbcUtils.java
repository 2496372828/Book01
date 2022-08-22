package com.sdjtxy.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
//使用提前封装好的德鲁伊Druid来实现jdbc功能实现代码极简化。
public class JdbcUtils {
    private static DataSource ds=null;
    private static ThreadLocal<Connection> conns=new ThreadLocal<>();
    static{

        try {
            Properties pro=new Properties();
            InputStream is=JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            pro.load(is);
            ds=DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return 返回数据库连接，如果返回null说明数据库连接失败
     * @throws SQLException
     */
    public static Connection getConnection(){
        Connection conn= conns.get();

        if(conn==null){
            try {
                //获取链接保存到threadLocal中
                conn= ds.getConnection();
                conns.set(conn);
                conn.setAutoCommit(false);


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return conn;

    }

    /**
     * 提交事务，并关闭释放连接
     */
    public static void commitAndClose(){
        Connection conn = conns.get();
        if(conn!=null){
            try {
                conn.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            conns.remove();
        }


    }
    public static void rollbackAndClose(){
        Connection conn=conns.get();
        if(conn!=null){
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        conns.remove();
        }


    }


}
