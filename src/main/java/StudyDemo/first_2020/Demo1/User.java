package StudyDemo.first_2020.Demo1;

import java.net.InetAddress;

/**
 * @ClassName User
 * @Description TODO
 * @date 2020/9/23 9:37
 */
public class User {
    private InetAddress address;
    private int port;
    private String name;

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public String getName() {
        return name;
    }

    public User(InetAddress address, int port, String name){
        this.address = address;
        this.port = port;
        this.name = name;
    }

    public String show(){
        return this.address+"\t"+this.port+"\t"+this.name;
    }

    public String showDetail(){
        return "address:"+address+"\tport:"+port+"\tname:"+name;
    }
}
