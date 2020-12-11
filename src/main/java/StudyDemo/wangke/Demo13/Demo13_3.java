package StudyDemo.wangke.Demo13;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Demo13_3 {
    static final java.lang.String FilePath = "src/Demo13_2.java";
    public static void main(String[] args){
        BufferedInputStream bufferedInputStream = null;
        try{
            bufferedInputStream = new BufferedInputStream(new FileInputStream(java.lang.String.valueOf(FilePath)));
            byte[] bytes = new byte[bufferedInputStream.available()];
            bufferedInputStream.read(bytes);
            java.lang.String temp = bytes.toString();
            System.out.println(temp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
