package JDBCTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUtilsTest01 {
    public static void main(String[] args) {
        Connection conn=null;
        try {
            conn=JDBCUtils.getConnectino();
            String sql="";
            PreparedStatement statement=conn.prepareStatement(sql);
            statement.execute();
            JDBCUtils.resourceClose(conn,statement,null);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {

        }
    }
}
