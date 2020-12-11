package StudyDemo.first_2020.Demo1;

import java.io.IOException;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
 * 服务器端，实现基于UDP的用户登陆
 */
public class server {
    // 1.创建服务器端DatagramSocket，指定端口
    private static DatagramSocket socket;
    // 2.创建数据报，用于接收客户端发送的数据
    private static byte[] data = new byte[1024];// 创建字节数组，指定接收的数据包的大小
    private static DatagramPacket packet = new DatagramPacket(data, data.length);

    //创建放置当前在线用户的list
    private static ArrayList<User> userList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //设置服务器端口
        socket= new DatagramSocket(8800);
        //获取服务器ip
        InetAddress localhost = InetAddress.getByName("localhost");
        // 3.接收客户端发送的数据
        System.out.println("****服务器端"+localhost+":8800已经启动，等待客户端发送数据****");
        while(true){
            socket.receive(packet);// 此方法在接收到数据报之前会一直阻塞
            String info = new String(data, 0, packet.getLength());
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            if(info.substring(0,info.indexOf('\t')).trim().equals("request")){
                //用户发来上线通知
                String name = info.substring(info.indexOf('\t')).trim();
                System.out.println("收到来自用户"+address+"\t"+port+"的上线申请");
                User tmpUser = new User(address,port,name);
                for(int i = 0;i < userList.size();i++){
                    //检查端口和昵称是否重复
                    int curPort = userList.get(i).getPort();
                    String curName = userList.get(i).getName();
                    if(curPort == tmpUser.getPort()) {
                        System.out.println("端口复用！踢出" + userList.get(i).show());
                        delUser(userList.get(i));
                    }
                    if(curName == tmpUser.getName()){
                        System.out.println("昵称重复！覆盖"+ userList.get(i).show());
                        delUser(userList.get(i));
                    }
                }
                //向用户池添加用户
                userList.add(tmpUser);
                //向所有用户发送新增用户提醒
                sendAll(tmpUser);
                continue;
            }else if(info.substring(0,info.indexOf('\t')).trim().equals("send")){
                //用户发送信息
                //获取信息内容
                String strInfo = info.substring(info.indexOf('\t')).trim();
                for(User curUser:userList){
                    if(address == curUser.getAddress()&&port == curUser.getPort()){
                        //读取发信人以及发信内容
                        sendAll(curUser.getName(),strInfo);
                    }
                }
            }
        }
    }


    private static void sendAll(User tmpUser){
        //向所有用户发送新增用户信息
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());
        //确认上线报文
        byte[] data_confirmInfo = "confirm\t".getBytes();
        DatagramPacket packet_confirm = new DatagramPacket(data_confirmInfo,data_confirmInfo.length,tmpUser.getAddress(),tmpUser.getPort());
        try {
            socket.send(packet_confirm);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //上线通知报文
        System.out.println(tmpUser.getName()+"已允许上线");
        String strInfo = "get\t系统消息\t"+time+"\t"+tmpUser.getName()+"已上线";
        byte[] data2 = strInfo.getBytes();
        for(int i = 0;i < userList.size();i++){
            User curUser = userList.get(i);
            DatagramPacket packet2 = new DatagramPacket(data2,data2.length,curUser.getAddress(),curUser.getPort());
            try {
                socket.send(packet2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void sendAll(String name,String info){
        //转发name用户发送的info信息
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());
        //报文内容
        System.out.println(name+"发送消息："+info);
        String strInfo = "get\t"+name+"\t"+time+"\t"+info;
        byte[] data2 = strInfo.getBytes();
        for(int i = 0;i < userList.size();i++){
            User curUser = userList.get(i);
            DatagramPacket packet2 = new DatagramPacket(data2,data2.length,curUser.getAddress(),curUser.getPort());
            try {
                socket.send(packet2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static void delUser(User tmpUser){
        //在用户重复时删除原来的用户
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());
        //下线通知报文
        System.out.println(tmpUser.getName()+"已下线");
        String strInfo = "get\t系统消息\t"+time+"\t"+tmpUser.getName()+"已下线";
        byte[] data2 = strInfo.getBytes();
        for(int i = 0;i < userList.size();i++){
            User curUser = userList.get(i);
            DatagramPacket packet2 = new DatagramPacket(data2,data2.length,curUser.getAddress(),curUser.getPort());
            try {
                socket.send(packet2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        userList.remove(tmpUser);
    }

}