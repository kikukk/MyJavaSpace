package Mysql.jdbc.util;



import Mysql.jdbc.domain.Emp;
import Mysql.jdbc.domain.JDBCDemo8;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class JDBCUtils {
    public static void main(String[] args) {
        List<Emp> list = new JDBCDemo8().findAll2();
        System.out.println(list);
        System.out.println(list.size());


    }

    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    //文件的读取，只需要读取一次即可拿到这些值使用静态代码块
    static{
        //读取资源文件，获取值

        try {
            //1.创建Properties集合类。
            Properties pro = new Properties();

            //获取src路径下的文件的方式-->类加载器
            
            ClassLoader classLoader =  JDBCUtils.class.getClassLoader();
            URL res = classLoader.getResource("jdbc.properties");
            System.out.println(res);
            String path = res.getPath();
//            System.out.println(path);

            //2.加载文件
            pro.load(new FileReader("D:\\kikukk\\WorkSpace\\MyJavaSpace\\src\\main\\resources\\jdbc.properties"));
            //3.获取数据，赋值
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
            //4.注册驱动
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        //获取连接
        return DriverManager.getConnection(url,user,password);

    }

    public static void close(ResultSet rs,Statement stmt, Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
