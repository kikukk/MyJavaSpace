package StudyDemo.first_2020.B20.Utils;

import StudyDemo.first_2020.B20.Resource;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author kikukk
 */
public class JdbcFunction {

    @Test
    public void test(){

        //user表操作
//        List<User> list = JDBCFuntion.getAllUser();
//        for(int i = 0;i < list.size();i++){
//            System.out.println(i+":"+list.get(i).getName());
//        }
//        System.out.println(list);
//        System.out.println(list.size());
//        System.out.println(checkPassword("04180429","04180429"));
//        altNetInfo("04180429","bbb",40);
//        System.out.println("getNameByAccount:"+getNameByAccount("04180429"));
//        System.out.println("getUserByAccount:"+getUserByAccount("04180429"));
//        System.out.println("checkName:"+checkName("kikukk"));
//        System.out.println(checkPassword("04180429","04180429"));
//        setPasswordByAccount("04180429","04180428");
//        insertUser(new User("04180404","04180404","asdf"));

        //history表操作
        insertHistory(new History("04180429","text","testInfo"));

    }

    private static final Statement STMT = Resource.getInstance().getStmt();

    public static String getNameByAccount(String account){
        ResultSet rs = null;
        try {
            //定义sql
            String sql = "select name from user where account=\""+account+"\";";
            //执行sql
            rs = STMT.executeQuery(sql);
            if(rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(rs,null,null);
        }
        return null;
    }

    public static void insertUser(User user){
        try {
            //定义sql
            String sql = "insert into user value(\""+user.getAccount()+"\",\""+user.getPassword()+"\",\""+user.getName()+"\");";
            //执行sql
            STMT.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setPasswordByAccount(String account,String password){
        try {
            //定义sql
            String sql = "update user set password=\""+password+"\" where account=\""+account+"\";";
            //执行sql
            STMT.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkAccount(String account){
        ResultSet rs = null;
        int returnResult = 0;
        try {
            //定义sql
            String sql = "select count(*) from user where account = \""+account+"\";";
            //执行sql
            rs = STMT.executeQuery(sql);
            if(rs.next()){
                returnResult = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(rs,null,null);
        }
        if(returnResult == 1){
            return true;
        }else{
            return false;
        }
    }

    public static boolean checkName(String name){
        ResultSet rs = null;
        int returnResult = 0;
        try {
            //定义sql
            String sql = "select count(*) from user where name = \""+name+"\";";
            //执行sql
            rs = STMT.executeQuery(sql);
            if(rs.next()){
                returnResult = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(rs,null,null);
        }
        if(returnResult == 1){
            return true;
        }else{
            return false;
        }
    }

    public static boolean checkPassword(String account,String password){
        ResultSet rs = null;
        int returnResult = 0;
        try {
            //定义sql
            String sql = "select count(*) from user where account = \""+account+"\" && password = \""+password+"\";";
            //执行sql
            rs = STMT.executeQuery(sql);
            if(rs.next()){
                returnResult = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(rs,null,null);
        }
        return returnResult == 1;
    }

    public static void insertHistory(History history){
        String fromAccount = history.getFromAccount();
        String time = history.getTime();
        String type = history.getType();
        String content = history.getContent();
        try {
            String sql = "insert into history value(\""+fromAccount+"\",\""+time+"\",\""+type+"\",\""+content+"\");";
            STMT.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<History> getHistory(){
        ResultSet rs = null;
        ArrayList<History> histories = new ArrayList<>();
        try {
            //定义sql
            String sql = "select * from history";
            //执行sql
            rs = STMT.executeQuery(sql);
            while(rs.next()){
                histories.add(new History(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(rs,null,null);
        }
        return histories;
    }
}
