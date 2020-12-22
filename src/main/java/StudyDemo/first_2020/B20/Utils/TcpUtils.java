package StudyDemo.first_2020.B20.Utils;

import StudyDemo.first_2020.B20.*;
import StudyDemo.first_2020.B20.SendInfoEvent.SendInfoManager;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author kikukk
 */
public class TcpUtils {
    private static ArrayList<User> userList;
    private static ArrayList<Socket> sockets;
    private static ArrayList<SendInfo> infoList;
    private static final SendInfoManager MANAGER = Resource.getInstance().getManager();

    private static class TcpUtilsHolder {
        private static final TcpUtils INSTANCE = new TcpUtils();
    }

    private TcpUtils(){
        try {
            Resource resource = Resource.getInstance();
            InetAddress localAddress = resource.getLocalAddress();
            int localPort = resource.getLocalPort();
            userList = resource.getUserList();
            sockets = resource.getSockets();
            infoList = resource.getInfoList();
            Socket connect = null;
            ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                    .setNameFormat("demo-pool-%d").build();
            ExecutorService pool = new ThreadPoolExecutor(5, 200,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
            try (ServerSocket service = new ServerSocket(localPort,5,localAddress)){
                System.out.println("****服务器端"+localAddress+":"+localPort+"已经启动，等待客户端发送数据****");
                while(true){
                    connect = service.accept();
                    sockets.add(connect);
                    //创建一个任务
                    ServiceTask serviceTask = new ServiceTask(connect);
                    //放入线程池等待运行
                    pool.execute(serviceTask);
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(connect!=null) {
                    connect.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ServiceTask implements Runnable{
        private final Socket socket;
        ServiceTask(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run() {
            try {
                InetAddress fromAddress = socket.getInetAddress();
                int fromPort = socket.getPort();
                System.out.println(fromAddress+":"+fromPort);
                InputStream in;

                in = socket.getInputStream();
                Function.dealStringPacket(new NetInfo(fromAddress,fromPort),in);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static Socket getSocket(NetInfo netInfo){
        InetAddress destAddress = netInfo.getAddress();
        int destPort = netInfo.getPort();
        for (Socket socket : sockets) {
            if (socket.getPort() == destPort && socket.getInetAddress().equals(destAddress)) {
                return socket;
            }
        }
        return null;
    }

    private static void sendTextBySocket(Socket socket,byte[] info){
        try {
            socket.getOutputStream().write(info);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendText(User tmpUser, byte[]info){
        sendText(tmpUser.getNetInfo(),info);
    }

    private static void sendText(NetInfo netInfo, byte[] info){
        sendTextBySocket(Objects.requireNonNull(getSocket(netInfo)),info);
    }

    private static void notifyLogin(String name){
        //向所有用户发送新增用户信息
        String time = getCurTime();
        //上线通知报文
        System.out.println(name+"已允许上线");
        String strInfo = "message\tnotice\tadmin\t"+time+"\t"+name+"已上线";
        try {
            sendContentToAll(strInfo.getBytes("GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static void sendContentToAll(byte[] info){
        for(User tmpUser:userList){
            sendText(tmpUser,info);
        }
    }

    private static void sendTextToAll(String name, byte[] sendContent){
        //转发name用户发送的info信息
        String time = getCurTime();
        //报文内容
        try {
            System.out.println(name+"发送消息："+new String(sendContent,"GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String strInfo = "message\ttext\t"+name+"\t"+time+"\t";
        try {
            sendContentToAll(addByteUp(strInfo.getBytes("GBK"),sendContent));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static void sendFileToAll(String name, byte[] sendContent, String type,String fileName){
        //转发name用户发送的info信息
        String time = getCurTime();
        //报文内容
        System.out.println(name+"发送"+type);
        String strInfo = "message\t"+type+"\t"+name+"\t"+time+"\t"+fileName+"\t"+sendContent.length+"\t";
        try {
            sendContentToAll(addByteUp(strInfo.getBytes("GBK"),sendContent));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static void delUser(User tmpUser){
        //在用户重复时删除原来的用户
        String time = getCurTime();
        //下线通知报文
        System.out.println(tmpUser.getName()+"已下线");
        String strInfo = "message\tnotice\tadmin\t"+time+"\t"+tmpUser.getName()+"已下线";
        try {
            sendContentToAll(strInfo.getBytes("GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        userList.remove(tmpUser);
    }

    public static TcpUtils getInstance() {return TcpUtilsHolder.INSTANCE;}

    public static void actionAddContent(){
        while(infoList.size()>0){
            dealRequestSend(infoList.get(0));
            infoList.remove(0);
        }
        MANAGER.fireWorkspaceClosed();
    }

    private static void dealRequestSend(SendInfo sendInfo){
        String target = sendInfo.getTarget();
        NetInfo netInfo = sendInfo.getFromAddress();
        byte[] sendContent = sendInfo.getSendInfo();
        User curUser = sendInfo.getFromUser();
        switch (target){
            case "single":
                sendText(netInfo,sendContent);
                break;
            case "many":
                sendTextToAll(curUser.getName(), sendContent);
                JdbcFunction.insertHistory(new History(curUser.getAccount(),"text",new String(sendContent,0,sendContent.length)));
                break;
            case "notifyLog":
                notifyLogin(curUser.getName());
                break;
            case "delUser":
                delUser(curUser);
                break;
            case "image":
                sendFileToAll(curUser.getName(), sendContent,"image",sendInfo.getFileName());
                JdbcFunction.insertHistory(new History(curUser.getAccount(),"image",sendInfo.getFileName()));
                break;
            case "file":
                sendFileToAll(curUser.getName(), sendContent,"file",sendInfo.getFileName());
                JdbcFunction.insertHistory(new History(curUser.getAccount(),"file",sendInfo.getFileName()));
                break;
            case "history":
                for(History history:JdbcFunction.getHistory()){
                    try {
                        sendText(curUser,history.getHistoryIntoSend().getBytes("GBK"));
                        Thread.sleep(200);
                    } catch (UnsupportedEncodingException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                System.out.println("default dealRequestSend");
                System.out.println(sendInfo);
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static byte[] addByteUp(byte[] byte1, byte[] byte2){
        byte[] byteAll = new byte[byte1.length+byte2.length];
        System.arraycopy(byte1, 0, byteAll, 0, byte1.length);
        System.arraycopy(byte2, 0, byteAll, byte1.length, byte2.length);
        return byteAll;
    }

    private static String getCurTime(){
        return Resource.getInstance().getDf().format(new Date());
    }
}
