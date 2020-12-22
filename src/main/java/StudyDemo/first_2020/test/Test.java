package StudyDemo.first_2020.test;

import java.io.*;
import java.net.Socket;

public class Test{//测试类
    public static void main(String[] args) {
//        String tmp = "1234";
//        byte[] bytes = new byte[1024];
//        bytes = tmp.getBytes();

        String a = "bt";
        byte[] bytea= a.getBytes();

        for(int i = 0;i < bytea.length;i++){
            System.out.print(bytea[i]+"|");
        }
    }

    @org.junit.Test
    public void func() throws IOException {
        //1.创建一个本地字节输入流FileInputStream对象,构造方法中绑定要读取的数据源
        FileInputStream fis = new FileInputStream("D:\\0Pictures\\xzq\\802941216.jpeg");
        //2.创建一个客户端Socket对象,构造方法中绑定服务器的IP地址和端口号
        Socket socket = new Socket("127.0.0.1",8800);
        //3.使用Socket中的方法getOutputStream,获取网络字节输出流OutputStream对象
        OutputStream os = socket.getOutputStream();
        //4.使用本地字节输入流FileInputStream对象中的方法read,读取本地文件
        int len = 0;
        int times = 0;
        byte[] bytes = new byte[10240000];
        os.write("bmessage\tpicture\t".getBytes());
//        while((len = fis.read(bytes))!=-1){
//            //5.使用网络字节输出流OutputStream对象中的方法write,把读取到的文件上传到服务器
//            os.write(bytes,0,len);
//        }

        InputStream in = socket.getInputStream();
        while((len = in.read(bytes))!=-1){
            System.out.println(new String(bytes,0,len));
        }
        fis.close();
        socket.close();
    }

    @org.junit.Test
    public void fun2() throws IOException {
        FileInputStream fis = new FileInputStream("D:\\0Pictures\\xzq\\802941216.jpeg");
        //2.创建一个客户端Socket对象,构造方法中绑定服务器的IP地址和端口号
        Socket socket = new Socket("127.0.0.1",8800);
        //3.使用Socket中的方法getOutputStream,获取网络字节输出流OutputStream对象
        OutputStream os = socket.getOutputStream();
        //4.使用本地字节输入流FileInputStream对象中的方法read,读取本地文件
        int len = 0;
        int times = 0;
        byte[] bytes = new byte[10240000];

        if((len = fis.read(bytes))!=-1){
            os.write(("bmessage\tpicture\t802941216.jpeg\t"+len+"\t").getBytes());
            os.write(bytes,0,len);
        }
        os.close();
        fis.close();
    }


}