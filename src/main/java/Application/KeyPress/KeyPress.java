package Application.KeyPress;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author kikukk
 */
public class KeyPress {
    private static Robot robot;
    InetAddress localAddress;
    int localPort;
    ServerSocket service;
    Socket socket;
    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private KeyPress(){
        try {
//            localAddress = InetAddress.getByName("25.62.70.236");
            localAddress = InetAddress.getByName("25.65.68.250");
//            localAddress = InetAddress.getByName("192.168.1.111");
            localPort = 9999;
            service = new ServerSocket(localPort,1,localAddress);
            System.out.println("****服务器端"+localAddress+":"+localPort+"已经启动，等待客户端发送数据****");
            socket = service.accept();
            InetAddress fromAddress = socket.getInetAddress();
            int fromPort = socket.getPort();
            System.out.println(fromAddress+":"+fromPort);
            InputStream in;
            in = socket.getInputStream();
            String strRec;
            while((strRec = getNextStringFromInputStream(in))!=null){
                int key = Integer.parseInt(strRec.substring(1));
                if(strRec.charAt(0) == 'P'){
                    keyPress(key);
                }
                else{
                    keyRelease(key);
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new KeyPress();
    }

    private static void keyPress(int keyEvent){
        robot.keyPress(keyEvent);
    }
    private static void keyRelease(int keyEvent){
        robot.keyRelease(keyEvent);
    }

    private static String getNextStringFromInputStream(InputStream in){
        byte[] bytes = new byte[1024];
        int len = 0;
        try {
            for (int c = in.read(); c != '\t'; c = in.read()) {
                if(c == -1){
                    in.close();
                    return null;
                }
                bytes[len++]= (byte) c;
            }
        } catch (IOException e) {
            return null;
        }
        return new String(bytes,0,len);
    }
}
