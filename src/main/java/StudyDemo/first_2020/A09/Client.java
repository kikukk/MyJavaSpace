package StudyDemo.first_2020.A09;

import StudyDemo.first_2020.A09.MessageTool.MessageManager;
import StudyDemo.first_2020.A09.ui.ChatWindows;
import StudyDemo.first_2020.A09.ui.ConnectWindows;
import StudyDemo.first_2020.A09.utils.MyBytes;
import StudyDemo.first_2020.A09.utils.NetUtils;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

/**
 * @author 14417
 * @ClassName client
 * @Description TODO
 * @date 2020/9/29 14:39
 */

public class Client {
    static ConnectWindows connectWindows = new ConnectWindows();
    public static ArrayList<Content> contents = new ArrayList<>();
    static ChatWindows chatWindows;

    static NetUtils netUtils = NetUtils.getInstance();
    static DatagramSocket socket = netUtils.getSocket();
    public static MessageManager manager = MessageManager.getInstance();

    public static void main(String[] args) {
        ReceivedThread receivedThread = new ReceivedThread();
        receivedThread.start();
    }

    private static class ReceivedThread extends Thread{
        @Override
        public void run() {
            while(true) {
                try {
                    // 1.创建数据报，用于接收服务器端响应的数据
                    byte[] data2 = new byte[102400000];
                    DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
                    // 2.接收服务器响应的数据
                    socket.receive(packet2);
                    // 3.读取数据
                    System.out.println("收到：" + packet2.getAddress() + ":" + packet2.getPort() + "的数据报");
                    User fromUser = new User(packet2.getAddress(), packet2.getPort());
                    dealInfo(new MyBytes(data2), fromUser);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private static void dealInfo(MyBytes info, User fromUser){
            String type = info.popFirstString();
            String mode = info.popFirstString();
            if ("connect".equals(type)) {
                switch (mode) {
                    case "getConnect":
                        netUtils.send(fromUser, "connect\tsetConnect\t".getBytes());
                        break;
                    case "setConnect":
                        System.out.println("与" + fromUser.getAddress() + "\t" + fromUser.getPort() + "建立连接");
                        connectWindows.setVisible(false);
                        chatWindows = new ChatWindows();
                        chatWindows.setUser(new User(fromUser.getAddress(), fromUser.getPort()));
                        chatWindows.setVisible(true);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + mode);
                }
            }else if ("contact".equals(type)) {
                contents.add(new Content(fromUser,mode,info));
                manager.fireWorkspaceOpened();
            } else {
                throw new IllegalStateException("Unexpected value: " + type);
            }
        }
    }

    public static void actionAddContent(){
        Thread.currentThread().setPriority(9);
        while(contents.size()>0){
            chatWindows.insert(contents.get(0));
            contents.remove(0);
        }
        manager.fireWorkspaceClosed();
    }
}
