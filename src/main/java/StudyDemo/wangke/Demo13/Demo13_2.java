package StudyDemo.wangke.Demo13;

import java.io.*;

public class Demo13_2 {
    private static final String FilePath = "src/test.txt";
    public static void main(String[] args) throws IOException {
        //ReadFile();
        writeFile();
        writeFile();

    }
    public static void writeFile() throws IOException {
        final String FilePath1 = "src/test_2.txt";//注意，此文件默认不存在
        OutputStream outputStream = new FileOutputStream(FilePath1);
        String content = "public static void main(String[] args){\n";
        content += "System.out.println(\"Hello World!\");\n}";
        outputStream.write(content.getBytes());//核心语句：将字符串转换成字节数组，写入到文件中
        //吸入完毕后一定记得关闭打开的资源
        outputStream.close();
    }
    public static void ReadFile()throws IOException{
        File file = new File(FilePath);//省略判断
        InputStream inputStream = new FileInputStream(file);
        //inputStream.available()获取输入流可以读取的文件大小（字节）
        //读取文件的基本操作-相对比较固定
        byte[] bytes = new byte[20000];
        inputStream.read(bytes);
//        int count = 0;
//        while((bytes[count] = (byte)inputStream.read())!= -1){
//            count++;
//        }
        String content = new String(bytes);//将读取出的字符串转换成字符串，以便打印
        System.out.println(content);
        inputStream.close();
    }
}
