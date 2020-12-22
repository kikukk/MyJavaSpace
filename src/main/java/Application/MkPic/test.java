package Application.MkPic;

import java.io.File;
import java.io.IOException;

public class test
{
    public static void main(String[] args) {
        open();
    }
    private static void open(){
        Runtime runtime = Runtime.getRuntime();
        Process p = null;
        String path ="src/main/java/Application/MkPic/MkPic.exe";
        try {
            p = runtime.exec(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}