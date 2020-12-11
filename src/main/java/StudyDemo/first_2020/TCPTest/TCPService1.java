package StudyDemo.first_2020.TCPTest;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPService1 {
    public static final String SERVICE_IP = "192.168.43.25";

    public static final int SERVICE_PORT = 8822;

    public static final char END_CHAR = '#';

    private static ArrayList<Socket> sockets = new ArrayList<>();

    public static void main(String[] args) {
        TCPService1 service1 = new TCPService1();
        service1.startService();
    }

    private void startService(){
        try {
            InetAddress address = InetAddress.getByName(SERVICE_IP);
            Socket connect = null;
            ExecutorService pool = Executors.newFixedThreadPool(5);
            try (ServerSocket service = new ServerSocket(SERVICE_PORT,5,address)){
                while(true){
                    connect = service.accept();
                    sockets.add(connect);
                    System.out.println(connect);
                    //创建一个任务
                    ServiceTask serviceTask = new ServiceTask(connect);
                    //放入线程池等待运行
                    pool.execute(serviceTask);
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(connect!=null)
                    connect.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ServiceTask implements Runnable{
        private Socket socket;

        ServiceTask(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run() {
            try {
                StringBuilder receiveMsg = new StringBuilder();
                InputStream in = socket.getInputStream();
                for (int c = in.read(); c != END_CHAR; c = in.read()) {
                    if(c ==-1)
                        break;
                    receiveMsg.append((char)c);
                }
                String str = receiveMsg.toString();
                System.out.println(str);

                int getPort = Integer.parseInt(str.substring(0,5));
                for(int i = 0;i < sockets.size();i++){
                    if(sockets.get(i).getPort() == getPort){
                        OutputStream out = sockets.get(i).getOutputStream();
                        out.write(str.getBytes());
                    }
                }

                String response = "Hello world " + receiveMsg.toString() + END_CHAR;
                Thread.currentThread().sleep(3000);
                for(int i = 0;i < sockets.size();i++){
                    OutputStream out = sockets.get(i).getOutputStream();
                    out.write(response.getBytes());
                }

            }catch (Exception e){
                e.printStackTrace();
            }finally {
//                if(socket!=null)
//                    try {
//                        socket.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
            }
        }
    }
}