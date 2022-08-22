package JDBCTest;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCUtils {

    private JDBCUtils(){

    }
    static ResourceBundle bundle=ResourceBundle.getBundle("db");
    static{
        try {
            Class.forName(bundle.getString("ClassName"));
            System.out.println("类加载完成！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     *  获取连接
     * @return 连接对象
     * @throws SQLException
     */
    public static Connection getConnectino() throws SQLException {
        Connection conn=DriverManager.getConnection(bundle.getString("URL"),bundle.getString("username"),bundle.getString("password"));

        return conn;

    }

    /**
     *  PrepareStatement 换成Statement 我们传来preparement--多态
     * @param conn
     * @param statement
     * @param result
     */
    public static void resourceClose(Connection conn, PreparedStatement statement, ResultSet result){
        if (result != null) {
            try {
                result.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (statement != null) {
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
