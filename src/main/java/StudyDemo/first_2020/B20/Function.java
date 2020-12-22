package StudyDemo.first_2020.B20;

import StudyDemo.first_2020.B20.SendInfoEvent.SendInfoManager;
import StudyDemo.first_2020.B20.Utils.JdbcFunction;
import StudyDemo.first_2020.B20.Utils.NetInfo;
import StudyDemo.first_2020.B20.Utils.TcpUtils;
import StudyDemo.first_2020.B20.Utils.User;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author kikukk
 */
public class Function {
    public static void main(String[] args) {
        new Function();
    }

    public Function() {
        TcpUtils.getInstance();
    }

    public static void dealStringPacket(NetInfo netInfo, InputStream in) throws IOException{
        InetAddress fromAddress = netInfo.getAddress();
        int fromPort = netInfo.getPort();
        Resource resource = Resource.getInstance();
        ArrayList<User> userList = resource.getUserList();
        ArrayList<SendInfo> infoList = resource.getInfoList();
        SendInfoManager manager = resource.getManager();

        while (true) {
            String order = getNextStringFromInputStream(in);
            if(order == null){
                return;
            }
            String type = getNextStringFromInputStream(in);
            if(type == null){
                return;
            }
//            System.out.println("order:" + order + "\ttype:" + type);
            switch (order) {
                case "request": {
                    //登录
                    switch (type) {
                        case "login": {
                            //request\tlogin\taccount\tpassword
                            String account = getNextStringFromInputStream(in);
                            String password = getAllStringFromInputStream(in);
                            System.out.println("acocunt:" + account + "\tpassword:" + password + "\t申请上线!");
                            if (JdbcFunction.checkAccount(account)) {
                                //账号存在
                                if (JdbcFunction.checkPassword(account,password)) {
                                    //账号存在，密码正确
                                    System.out.println("密码正确!");
                                    userList.add(new User(account, password, netInfo));
                                    infoList.add(new SendInfo("single", Objects.requireNonNull(getUserByAccount(account)), ("confirm\tlogin\tsuccess").getBytes("GBK")));

                                    User curUser = Objects.requireNonNull(getUserByNetInfo(new NetInfo(fromAddress, fromPort)));
                                    System.out.println("向"+curUser.getAccount()+"发送聊天记录");
                                    infoList.add(new SendInfo("history",curUser,new byte[1]));

                                    infoList.add(new SendInfo("notifyLog", Objects.requireNonNull(getUserByAccount(account)), ("").getBytes("GBK")));
                                }
                                else{
                                    //账号存在，密码错误
                                    System.out.println("密码错误");
                                    infoList.add(new SendInfo("single",new User(netInfo),("confirm\tlogin\terror\tpassword").getBytes("GBK")));
                                }
                            }
                            else{
                                //账号不存在
                                System.out.println("账号不存在");
                                infoList.add(new SendInfo("single",new User(netInfo),("confirm\tlogin\terror\taccount").getBytes("GBK")));
                            }
                            manager.fireWorkspaceOpened();
                            break;
                        }
                        case "signup": {
                            //request\tsignup\taccount\tpassword\tname
                            String account = getNextStringFromInputStream(in);
                            String password = getNextStringFromInputStream(in);
                            String name = getAllStringFromInputStream(in);
                            User tmpUser = new User(account, password,name,new NetInfo(fromAddress, fromPort));
                            System.out.println("acocunt:" + account + "\tpassword:" + password + "\tname:" + name + "\t申请注册!");
                            if (JdbcFunction.checkAccount(account)) {
                                System.out.println("账号已存在!");
                                infoList.add(new SendInfo("single", new User(new NetInfo(fromAddress, fromPort)), "confirm\tsignup\terror\taccount".getBytes("GBK")));
                                manager.fireWorkspaceOpened();
                                break;
                            }
                            if (JdbcFunction.checkName(name)) {
                                System.out.println("昵称重复!");
                                infoList.add(new SendInfo("single", new User(new NetInfo(fromAddress, fromPort)), "confirm\tsignup\terror\tname".getBytes("GBK")));
                                manager.fireWorkspaceOpened();
                                break;
                            }
                            System.out.println("注册成功!");
                            JdbcFunction.insertUser(tmpUser);
//                            userList.add(tmpUser);
                            infoList.add(new SendInfo("single", tmpUser, "confirm\tsignup\tsuccess".getBytes("GBK")));
//                            infoList.add(new SendInfo("notifyLog", tmpUser, "".getBytes("GBK")));
                            manager.fireWorkspaceOpened();
                            break;
                        }
                        case "change": {
                            //request\tchange\taccount\tpassword_1\tpassword_2\tname
                            String account = getNextStringFromInputStream(in);
                            String originPasswd = getNextStringFromInputStream(in);
                            String newPasswd = getNextStringFromInputStream(in);
                            String name = getAllStringFromInputStream(in);
                            System.out.println("acocunt:" + account + "\t申请修改信息!");
                            User curUser = new User(account, originPasswd, new NetInfo(fromAddress, fromPort));
                            if (JdbcFunction.checkName(name) && !Objects.requireNonNull(JdbcFunction.getNameByAccount(account)).equals(name)) {
                                //更改的用户名有重复
                                System.out.println("昵称重复!");
                                infoList.add(new SendInfo("single", curUser, "confirm\tchange\terror\tname".getBytes("GBK")));
                                manager.fireWorkspaceOpened();
                                break;
                            }
                            if (Objects.requireNonNull(getUserByAccount(account)).getPassword().equals(originPasswd) && !originPasswd.equals(newPasswd)) {
                                //密码正确，可修改密码
                                System.out.println("修改成功!");
                                curUser.altPassword(newPasswd);
                                infoList.add(new SendInfo("single", curUser, "confirm\tchange\tsuccess".getBytes("GBK")));
                                manager.fireWorkspaceOpened();
                                break;
                            } else {
                                //密码错误，禁止修改密码
                                System.out.println("密码错误!");
                                infoList.add(new SendInfo("single", curUser, "confirm\tchange\terror\tpassword".getBytes("GBK")));
                                manager.fireWorkspaceOpened();
                            }
                            break;
                        }
                        default:
                            System.out.println("order:" + order + "|type:" + type + "指令错误！！！");
                    }
                    break;
                }
                case "message": {
                    switch (type) {
                        case "text": {
                            //message\ttext\tcurInfo
                            String curInfo = getAllStringFromInputStream(in);
                            assert curInfo != null;
                            infoList.add(new SendInfo("many", Objects.requireNonNull(getUserByNetInfo(netInfo)), curInfo.getBytes("GBK")));
                            manager.fireWorkspaceOpened();
                            break;
                        }
                        case "image": {
                            //message\timage\t文件名字符串\t文件大小\t（文件内容）
                            String fileName = getNextStringFromInputStream(in);
                            int fileSize = Integer.parseInt(Objects.requireNonNull(getNextStringFromInputStream(in)));
                            System.out.println("图片名称:"+fileName+"\t"+"图片大小:"+fileSize);
                            byte[] fileContent = new byte[fileSize];
                            in.read(fileContent);
                            writeFileToLocal(fileName,fileSize,fileContent);
                            infoList.add(new SendInfo("image", Objects.requireNonNull(getUserByNetInfo(netInfo)), fileName, fileContent));
                            manager.fireWorkspaceOpened();
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                        case "file": {
                            //message\tfile\t文件名字符串\t文件大小\t（文件内容）
                            String fileName = getNextStringFromInputStream(in);
                            int fileSize = Integer.parseInt(Objects.requireNonNull(getNextStringFromInputStream(in)));
                            byte[] fileContent = new byte[fileSize];
                            in.read(fileContent);
                            infoList.add(new SendInfo("file", Objects.requireNonNull(getUserByNetInfo(netInfo)), fileName, fileContent));
                            manager.fireWorkspaceOpened();
                            break;
                        }
                        default: {
                            System.out.println("order:" + order + "|type:" + type + "指令错误！！！");
                        }
                    }
                    break;
                }
                case "ask": {
                    switch (type) {
                        case "connect": {
                            //ask\tconnect\t
                            infoList.add(new SendInfo("single", new User(new NetInfo(fromAddress, fromPort)), "reply\tconnect".getBytes("GBK")));
                            manager.fireWorkspaceOpened();
                            break;
                        }
                        case "account": {
                            //ask\taccount\t账号
                            String account = getAllStringFromInputStream(in);
                            if(JdbcFunction.checkAccount(account)){
                                //有此账号
                                infoList.add(new SendInfo("single", new User(new NetInfo(fromAddress, fromPort)), ("reply\taccount\t" + account + "\texisted").getBytes("GBK")));
                            }
                            else{
                                //无此账号
                                infoList.add(new SendInfo("single", new User(new NetInfo(fromAddress, fromPort)), ("reply\taccount\t" + account + "\tnoexisted").getBytes("GBK")));
                            }
                            manager.fireWorkspaceOpened();
                            break;
                        }
                        case "name": {
                            //ask\tname\t用户名
                            String name = getAllStringFromInputStream(in);
                            if(JdbcFunction.checkName(name)){
                                //有此用户名
                                infoList.add(new SendInfo("single", new User(new NetInfo(fromAddress, fromPort)), ("reply\tname\t" + name + "\texisted").getBytes("GBK")));
                            }
                            else{
                                //无此用户名
                                infoList.add(new SendInfo("single", new User(new NetInfo(fromAddress, fromPort)), ("reply\tname\t" + name + "\tnoexisted").getBytes("GBK")));
                            }
                            manager.fireWorkspaceOpened();
                            break;
                        }
                        case "onlinenumber": {
                            //ask\tonlinenumber\t
                            String number = String.valueOf(userList.size());
                            infoList.add(new SendInfo("single", new User(new NetInfo(fromAddress, fromPort)), ("reply\tonlinenumber\t" + number).getBytes("GBK")));
                            manager.fireWorkspaceOpened();
                            break;
                        }
                        default: {
                            System.out.println("order:" + order + "|type:" + type + "指令错误！！！");
                        }
                    }
                    break;
                }
                default: {
                    System.out.println("没有这种指令！！！");
                }
            }
        }
    }

    public static String getNextStringFromInputStream(InputStream in) throws UnsupportedEncodingException {
        byte[] bytes = new byte[1024];
        int len = 0;
        try {
            for (int c = in.read(); c != '\t'; c = in.read()) {
                if(c == -1){
                    offLine(in);
                    in.close();
                    return null;
                }
                bytes[len++]= (byte) c;
            }
        } catch (IOException e) {
            offLine(in);
            return null;
        }
        return new String(bytes,0,len,"GBK");
    }
    private static String getAllStringFromInputStream(InputStream in) {
        int len;
        byte[] bytes = new byte[1024];
        try {
            if ((len = in.read(bytes)) != -1) {
                return new String(bytes, 0, len,"GBK");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void writeFileToLocal(String fileName,int fileSize,byte[] fileContent){
        File file = new File("D:\\ftpServer\\TcpTest\\serverReceive");
        try {
            FileOutputStream fos = new FileOutputStream(file+"\\"+fileName);
            fos.write(fileContent,0,fileSize);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("图片内容开始打印");
        for(int i = 0;i < fileContent.length;i++){
            System.out.print(fileContent[i]+" ");
        }
        System.out.println("\n图片内容打印完成");
    }

    private static void offLine(InputStream in){
        Socket socket = getSocketByInputStream(in);
        User user = getUserBySocket(socket);
        assert socket != null;
        System.out.println(socket.getInetAddress()+"\t"+socket.getPort()+"\t"+user+"\t"+"已离线");
        Resource.getInstance().getSockets().remove(socket);
        Resource.getInstance().getUserList().remove(user);
    }

    private static Socket getSocketByInputStream(InputStream in){
        ArrayList<Socket> sockets = Resource.getInstance().getSockets();
        for(Socket socket:sockets){
            try {
                if(socket.getInputStream() == in){
                    return socket;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    private static User getUserBySocket(Socket socket){
        ArrayList<User> userList = Resource.getInstance().getUserList();
        for(User user:userList){
            if(user.getNetInfo().getAddress() == socket.getInetAddress()&&user.getNetInfo().getPort() == socket.getPort()){
                return user;
            }
        }
        return null;
    }
    private static User getUserByAccount(String account) {
        ArrayList<User> userList = Resource.getInstance().getUserList();
        for (User user : userList) {
            if (user.getAccount().equals(account)) {
                return user;
            }
        }
        return null;
    }
    private static User getUserByNetInfo(NetInfo netInfo) {
        ArrayList<User> userList = Resource.getInstance().getUserList();
        for (User user : userList) {
            if (user.getNetInfo().getAddress() == netInfo.getAddress() && user.getNetInfo().getPort() == netInfo.getPort()) {
                return user;
            }
        }
        return null;
    }

}


