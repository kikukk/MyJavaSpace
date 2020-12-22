package StudyDemo.first_2020.B20.Utils;

import java.sql.*;

/**
 * @author kikukk
 */
public class JdbcUtils {
    private static String url;
    private static String user;
    private static String password;
    //文件的读取，只需要读取一次即可拿到这些值使用静态代码块
    static{
        //3.获取数据，赋值
        url = "jdbc:mysql:///letchat?serverTimezone=GMT%2B8";
        user = "root";
        password = "root";
        String driver = "com.mysql.cj.jdbc.Driver";
        //4.注册驱动
        try {
            Class.forName(driver);
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
