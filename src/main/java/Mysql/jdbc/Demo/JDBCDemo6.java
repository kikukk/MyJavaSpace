package Mysql.jdbc.Demo;

import java.sql.*;

public class JDBCDemo6 {
    public static void main(String[] args) {
        /*
        * 执行DDL语句(查询)
        * */
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql:///test?serverTimezone=GMT%2B8","root","root");
            //3.定义sql
            String sql = "select * from dept";
            //4.获取执行sql对象
            stmt = conn.createStatement();
            //5.执行sql
            rs = stmt.executeQuery(sql);
            //6.处理结果
            //6.1让数据向下移动一行
            while(rs.next()){
                //6.2获取数据
                int id = rs.getInt(1);
                String name = rs.getString("name");
                System.out.println(id + "---" + name);

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //7.释放资源

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
