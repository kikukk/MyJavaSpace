//package jdbc.ConnectionDemo;
//
//public class ConnectionDemo01 {
//    try
//    private static final String JDBC驱动类 = ;
//
//    {
//        Class.forName(JDBC驱动类);
//    } catch (ClassNotFoundException e) {
//        System.out.println("无法找到驱动类");
//    }
//        try {
//        Connection con=DriverManager.getConnection(JDBC URL,数据库用户名,密码);
//        Statement stmt = con.createStatement();
//        ResultSet rs = stmt.executeQuery("SELECT a, b, c FROM Table1");
//        while (rs.next()) {
//            int x = rs.getInt("a");
//            String s = rs.getString("b");
//            float f = rs.getFloat("c");
//        }
//        con.close();
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//}
