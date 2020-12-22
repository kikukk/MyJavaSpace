package ex8;

import java.io.*;
public class ex8_1 {
    public static void main(String[] args) {
        char[][] nums = new char[5][10];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
                nums[i][j] = ' ';
            }
            for (int j = 0; j < 9 - 2 * i; j++) {
                System.out.print("*");
                nums[i][j + i] = '*';
            }
            System.out.println();
        }
        try//使用File进行读取及存储
        {
            String m_fileName = "src/ex8/text.txt";
            FileOutputStream f = new FileOutputStream(m_fileName);
            PrintStream out = new PrintStream(f);
            for(int i = 0;i < 5;i++){
                for(int j = 0;j < 10;j++){
                    out.print(nums[i][j]);
                }
                out.println();
            }
            f.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try//使用Buffer进行存储及读取
//        {
//
//            System.out.println("开始写入！");
//            BufferedWriter bf = new BufferedWriter(new FileWriter(new File(m_fileName)));
//            String tempStr;
//            for(int i = 0;i < 5;i++){
//                tempStr = new String(nums[i]);
//                bf.write(tempStr+'\n');
//            }
//            bf.close();
//            System.out.println("写入完成！");
//
//            System.out.println("开始读取！");
//            BufferedReader br = new BufferedReader(new FileReader(new File(m_fileName)));
//            String tempString;
//            char[][] tempCh = new char[5][10];
//            int l = 0;
//            while((tempString = br.readLine())!=null){
//                tempCh[l++] = tempString.toCharArray();
//            }
//            for (int i = 0;i < 5;i++){
//                for(int j = 0;j < 9;j++){
//                    System.out.print(tempCh[i][j]);
//                }
//                System.out.println();
//            }
//            System.out.println("读取完毕！");
//            br.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
