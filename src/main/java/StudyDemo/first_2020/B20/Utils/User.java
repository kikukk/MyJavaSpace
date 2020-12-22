package StudyDemo.first_2020.B20.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author kikukk
 * @date 2020/9/23 9:37
 */
public class User {
    private String account;
    private String password;
    private String name;
    private NetInfo netInfo;

    public User(ResultSet rs){
        try {
            account = rs.getString("account");
            password = rs.getString("password");
            name = rs.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User(String account, String password, NetInfo netInfo){
        this.account = account;
        this.password = password;
        this.name = JdbcFunction.getNameByAccount(account);
        this.netInfo = netInfo;
    }

    public User(String account,String password,String name){
        this.account = account;
        this.password = password;
        this.name = name;
    }

    public User(NetInfo netInfo){
        this.netInfo = netInfo;
    }

    public User(String account, String password, String name, NetInfo netInfo){
        this.account = account;
        this.password = password;
        this.name = name;
        this.netInfo = netInfo;
    }

    public NetInfo getNetInfo() {
        return netInfo;
    }

    public String getName() {
        return name;
    }

    public String getAccount(){return account;}

    public String getPassword(){return password;}

    public void setPassword(String password) {
        this.password = password;
    }

    public void altPassword(String password){
        setPassword(password);
        JdbcFunction.setPasswordByAccount(this.account,password);
    }

    public String show(){
        return this.netInfo.getAddress()+"\t"+this.netInfo.getPort()+"\t"+this.getName();
    }

    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", netInfo=" + netInfo +
                '}';
    }
}
