package Mysql.Function.druid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DruidDemo2 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        //使用新的工具类
        //完成添加操作：给account表添加一条记录
        try {
            conn = JDBCUtils.getConnection();
            String sql = "insert into account values(null,?,?)";
            pstmt  = conn.prepareStatement(sql);
            pstmt.setString(1,"王五");
            pstmt.setInt(2,100);
            int count = pstmt.executeUpdate();
            System.out.println(count);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCUtils.close(pstmt,conn);
        }
    }
}
