package StudyDemo.first_2020.TCPTest;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @ClassName TCPClient
 * @Description TODO
 * @date 2020/10/28 12:00
 */
public class TCPClient {

    public static void main(String[] args) {
        TCPClient client = new TCPClient();
        SimpleDateFormat format = new SimpleDateFormat("hh-MM-ss");
        Scanner scanner = new Scanner(System.in);
        while(true){
            String msg = scanner.nextLine();
            if("#".equals(msg)){break;}
            //打印响应的数据
            System.out.println("send time : " + format.format(new Date()));
            System.out.println(client.sendAndReceive(TCPService1.SERVICE_IP,TCPService1.SERVICE_PORT,msg));
            System.out.println("receive time : " + format.format(new Date()));
        }
    }

    private String sendAndReceive(String ip, int port, String msg){
        //这里比较重要，需要给请求信息添加终止符，否则服务端会在解析数据时，一直等待
        msg = msg+TCPService.END_CHAR;
        StringBuilder receiveMsg = new StringBuilder();
        //开启一个链接，需要指定地址和端口
        try (Socket client = new Socket(ip, port)){
            //向输出流中写入数据，传向服务端
            OutputStream out = client.getOutputStream();
            out.write(msg.getBytes());

            //从输入流中解析数据，输入流来自服务端的响应
            InputStream in = client.getInputStream();
            for (int c = in.read(); c != TCPService.END_CHAR; c = in.read()) {
                if(c==-1)
                    break;
                receiveMsg.append((char)c);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return receiveMsg.toString();
    }
}
