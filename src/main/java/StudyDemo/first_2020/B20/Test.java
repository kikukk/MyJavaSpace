package StudyDemo.first_2020.B20;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author kikukk
 */
public class Test {
    @org.junit.Test
    public void test(){
        Test test = new Test();
        test.send();
    }

    @org.junit.Test
    public void ioFile() throws IOException {
        File file = new File("D:\\ftpServer\\TcpTest\\802941216.jpeg");
        byte[] pic = new byte[(int) file.length()];
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            fis.read(pic);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String strFile = new String(pic);

        byte[] bytes = strFile.getBytes();
        FileOutputStream fos = new FileOutputStream("D:\\ftpServer\\TcpTest\\1.jpeg");
        try {
            fos.write(bytes,0,bytes.length);

        } catch (IOException e) {
            e.printStackTrace();
        }
        assert fis != null;
        fis.close();
        fos.close();
    }


    @org.junit.Test
    public void sendAndReceive(){
        //开启一个链接，需要指定地址和端口
        try (Socket client = new Socket("192.168.43.25", 8800)){
            //登录
            String msg = "request\tlogin\t1111\t1111";
            OutputStream out = client.getOutputStream();
            out.write(msg.getBytes("GBK"));
            //返回登录信息
            InputStream in = client.getInputStream();
            int len;
            byte[] bytes = new byte[1024];
            if ((len = in.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, len,"GBK"));
            }
            if ((len = in.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, len,"GBK"));
            }

            //上传图片
//            String fileName = "1070191950.jpeg";
//            File file = new File("D:\\ftpServer\\TcpTest\\"+fileName);
            String fileName = "hi.jpg";
            File file = new File("D:\\图片\\Saved Pictures\\"+fileName);
            bytes = new byte[(int) file.length()];
            msg = "message\timage\t"+fileName+"\t"+file.length()+"\t";
            out.write(msg.getBytes("GBK"));
            FileInputStream fis = new FileInputStream(file);
            fis.read(bytes);
            out.write(bytes);

            //接收返回的图片
            //-message\tfile\t发送方用户名\t发送时间\t文件名\t文件大小\t文件内容
            FileOutputStream fos = new FileOutputStream("D:\\ftpServer\\TcpTest\\clientReceive\\"+fileName);
            for(int i = 0;i < 6;i++){
                System.out.println(getNextStringFromInputStream(in));
            }
            bytes = new byte[(int) file.length()];
            in.read(bytes,0, (int) file.length());
            fos.write(bytes);

            fis.close();
            fos.close();
            in.close();
            out.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @org.junit.Test
    public void send(){
        //开启一个链接，需要指定地址和端口
        try (Socket client = new Socket("192.168.43.25", 8800)) {
            //登录
            String msg = "request\tlogin\t1111\t1111";
            OutputStream out = client.getOutputStream();
            out.write(msg.getBytes("GBK"));
            //返回登录信息
            InputStream in = client.getInputStream();
            int len;
            byte[] bytes = new byte[1024];
            if ((len = in.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, len, "GBK"));
            }
            if ((len = in.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, len, "GBK"));
            }

            //上传图片
//            String fileName = "1070191950.jpeg";
//            File file = new File("D:\\ftpServer\\TcpTest\\"+fileName);
            String fileName = "hi.jpg";
            File file = new File("D:\\图片\\Saved Pictures\\" + fileName);
            bytes = new byte[(int) file.length()];
            msg = "message\timage\t" + fileName + "\t" + file.length() + "\t";
            out.write(msg.getBytes("GBK"));
            FileInputStream fis = new FileInputStream(file);
            fis.read(bytes);
            out.write(bytes);

            fis.close();
            in.close();
            out.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getNextStringFromInputStream(InputStream in) throws UnsupportedEncodingException {
        byte[] bytes = new byte[1024];
        int len = 0;
        try {
            for (int c = in.read(); c != '\t'; c = in.read()) {
                bytes[len++]= (byte) c;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes,0,len,"GBK");
    }

}
