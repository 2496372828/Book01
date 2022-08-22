package com.sdjtxy.utils;
//封装jdbc工具类
//注意：配置文件放到resources下，放到src下ResourceBundle无法拿到
import java.sql.*;
import java.util.ResourceBundle;

public class JdbcUtilsCopy {
    //工具类一般设置私有构造方法，当我们调用工具类中的静态方法时，工具类就会类加载，执行static代码块，进行ClassName中类加载
    private JdbcUtilsCopy(){
    }
    static ResourceBundle bundle=ResourceBundle.getBundle("db");
    //类加载驱动
    static {
        try {
            Class.forName(bundle.getString("ClassName"));
            System.out.println("类加载完成！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //获取链接
    public static Connection getConnection() throws SQLException {
    Connection conn= DriverManager.getConnection(bundle.getString("URL"),bundle.getString("username"), bundle.getString("password"));
    Statement statement=conn.createStatement();

    return  conn;

    }
    public static void sourceClose(Connection conn, Statement statement, ResultSet result){
        if (result != null) {
            try {
                result.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if ( statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

    }

}
