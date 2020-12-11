package StudyDemo.first_2020.B20;

import StudyDemo.first_2020.B20.SendInfoEvent.SendInfoListener;
import StudyDemo.first_2020.B20.SendInfoEvent.SendInfoManager;
import StudyDemo.first_2020.B20.Utils.JdbcUtils;
import StudyDemo.first_2020.B20.Utils.User;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * @author kikukk
 */
public class Resource {
    private final ArrayList<User> userList = new ArrayList<>();
    private final ArrayList<Socket> sockets = new ArrayList<>();
    private final ArrayList<SendInfo> infoList = new ArrayList<>();

    private Connection conn = null;
    private Statement stmt = null;

    private InetAddress localAddress;

    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static Resource resource = new Resource();

    private final SendInfoManager manager = new SendInfoManager();


    private Resource(){
        manager.addMessageListener(new SendInfoListener());
        try {
            localAddress = InetAddress.getByName("25.62.70.236");
            conn = JdbcUtils.getConnection();
            stmt = conn.createStatement();
        } catch (UnknownHostException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Resource getInstance() {
        if (resource == null){
            resource = new Resource();
        }
        return resource;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public ArrayList<Socket> getSockets() {
        return sockets;
    }

    public ArrayList<SendInfo> getInfoList(){return infoList;}

    public InetAddress getLocalAddress() {
        return localAddress;
    }

    public int getLocalPort() {
        return 8800;
    }

    public SimpleDateFormat getDf() {
        return df;
    }

    public SendInfoManager getManager(){return manager;}

    public Connection getConn() {
        return conn;
    }

    public Statement getStmt() {
        return stmt;
    }
}
