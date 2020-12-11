package Application.openApp;

import java.io.IOException;
/**
 * @author 14417
 */
public class openApp extends Thread{

    public static void main(String[] args) {
        Thread thread = new openApp();
        thread.start();
    }

    private void open(){
        Runtime runtime = Runtime.getRuntime();
        Process p = null;
        String path ="D:\\software\\useful\\HQPlayer 4 Desktop\\HQPlayer4Desktop.exe";
        try {
            p = runtime.exec(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                open();
                Thread.sleep(1800000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



