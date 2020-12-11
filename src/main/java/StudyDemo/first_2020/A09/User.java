package StudyDemo.first_2020.A09;

import java.net.InetAddress;
import java.util.ArrayList;

/**
 * @ClassName User
 * @Description TODO
 * @date 2020/9/29 14:48
 */

public class User {
    private InetAddress address;
    private int port;
    private ArrayList<User> userList = new ArrayList<>();

    public User(InetAddress address,int port){
        this.address = address;
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }
}
