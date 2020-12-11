package StudyDemo.first_2020.A09.utils;

import StudyDemo.first_2020.A09.User;

import java.io.IOException;
import java.net.*;

/**
 * @author 14417
 * @ClassName NetUtils
 * @Description TODO
 * @date 2020/10/28 9:57
 */
public class NetUtils {
    DatagramSocket socket;
    InetAddress localAddress;
    int localPort;
    private static final NetUtils instance = new NetUtils();
    private NetUtils(){
        try {//定义参数
            localAddress = InetAddress.getLocalHost();
            System.out.println(localAddress.getHostAddress());
            socket = new DatagramSocket();
            localPort = 8800;
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
    }

    public static NetUtils getInstance(){
        return instance;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public InetAddress getLocalAddress() {
        return localAddress;
    }

    public int getLocalPort() {
        return localPort;
    }

    public void send(User user,byte[] info){
        System.out.println("向"+user.getAddress()+":"+user.getPort()+"发送数据报");
        InetAddress destIp = user.getAddress();
        int destPort = user.getPort();
        //创建数据报，包含发送的数据信息
        DatagramPacket packet;
        try {
            packet = new DatagramPacket(info, info.length, destIp, destPort);
            //向服务器端发送数据报
            socket.send(packet);
        } catch (IOException unknownHostException) {
            unknownHostException.printStackTrace();
        }
    }
}
