package Mysql.Function.c3p0;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Demo2 {
    public static void main(String[] args) throws SQLException {
        DataSource ds = null;
        //1.获取DataSource,使用默认配置
        //ds = new ComboPooledDataSource();
        //1.1获取DataSource.使用指定名称配置
        //ds = new ComboPooledDataSource("other");

        //2.获取连接
        for(int i = 1;i < 12;i++){
            Connection conn = ds.getConnection();
            System.out.println(i + ":" + conn);
            if(i == 5)conn.close();
        }
    }
}
