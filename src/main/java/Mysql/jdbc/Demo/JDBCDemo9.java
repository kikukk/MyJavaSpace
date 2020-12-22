package Mysql.jdbc.Demo;


import Mysql.jdbc.util.JDBCUtils;

import java.sql.*;
import java.util.Scanner;

public class JDBCDemo9 {
/*
* 练习：
*   需求
*   1.通过键盘录入用户名和密码
*   2.判断用户是否登录成功
* */
    public static void main(String[] args) {
        //1.键盘录入，接收用户名和密码
        Scanner input = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = input.nextLine();
        System.out.println("请输入密码：");
        String password = input.nextLine();
        //2.调用方法
        boolean flag = new JDBCDemo9().login2(username,password);
        //3.判断结果，输出不同语句
        if(flag){
            //登录成功
            System.out.println("登录成功！");
        }else{
            //登录失败
            System.out.println("用户名或密码错误！");
        }

    }

    //登录方法：
    public boolean login(String username,String password){
        if(username == null||password == null)return false;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        //连接数据库判断是否登录成功
        try {
            //1.获取连接
            conn = JDBCUtils.getConnection();
            //2.定义sql
            String sql = "select * from user where username = '"+username+"' and password = '"+password+"'";
            //3.获取执行sql的对象
            stmt = conn.createStatement();
            //4.执行查询
            rs = stmt.executeQuery(sql);
            //5.判断
            return rs.next();//如果有下一行，则返回true

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,stmt,conn);
        }
        return false;
    }

    //登录方法,使用PrepareStatement实现：
    public boolean login2(String username,String password){
        if(username == null||password == null)return false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        //连接数据库判断是否登录成功
        try {
            //1.获取连接
            conn = JDBCUtils.getConnection();
            //2.定义sql
            String sql = "select * from user where username = ? and password = ?";
            //3.获取执行sql的对象
            pstmt = conn.prepareStatement(sql);
            //给？赋值
            pstmt.setString(1,username);
            pstmt.setString(2,password);

            //4.执行查询
            rs = pstmt.executeQuery();
            //5.判断
            return rs.next();//如果有下一行，则返回true

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        return false;
    }

}
