package Mysql.jdbc.Demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcDemo1 {
    public static void main(String[] args) throws Exception {
        //快速入门
        //1.导入驱动jar包
        //2.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //3.获取数据库连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8","root","root");
        //4.定义sql语句
        String sql = "update emp set dept_id = 1 where emp.id = 1";
        //5.获取执行sql的对象Statement
        Statement stmt = conn.createStatement();
        //6.执行sql
        int count = stmt.executeUpdate(sql);
        //7.处理结果
        System.out.println(count);
        //8.释放资源
        stmt.close();
        conn.close();


    }
}
