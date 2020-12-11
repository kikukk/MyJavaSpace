package Mysql.jdbc.Demo;


import Mysql.jdbc.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCDemo10 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;

        try {
            conn = JDBCUtils.getConnection();
            //开启事务
            conn.setAutoCommit(false);
            String sql1 = "update account set balance = balance - ? where id = ?";
            String sql2 = "update account set balance = balance + ? where id = ?";

            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);

            pstmt1.setInt(1,50);
            pstmt1.setInt(2,1);

            pstmt2.setInt(1,50);
            pstmt2.setInt(2,2);

            pstmt1.executeUpdate();
            int i = 3/0;
            pstmt2.executeUpdate();

            conn.commit();
            //提交事务
        } catch (SQLException e) {
            //事务回滚
            try {
                if(conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
        }finally {
            JDBCUtils.close(null,pstmt1,conn);
            JDBCUtils.close(null,pstmt2,null);


        }


    }

}
