package Application.ShareScreen.System.Worker;

import Application.ShareScreen.System.LocalComputer;
import Application.ShareScreen.System.Screenshot;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author matrixy
 * @date 2018/4/9
 */
public class CaptureWorker extends BaseWorker {
    private void captureAndStore() {
        ScreenImages.addScreenshot(LocalComputer.captureScreen());
    }

    @Override
    public void run() {
        while (!this.isTerminated()) {
            try {
                captureAndStore();
                // TODO: FPS控制
                sleep(1);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @Test
    public void test() throws AWTException {
        Robot robot = new Robot();
        Screenshot screenshot = new Screenshot(robot.createScreenCapture(new Rectangle(0,0,500,500)));
        try {
            FileOutputStream fos = new FileOutputStream("D:\\ftpServer\\TcpTest\\1.png");
            for(int i = 0;i < screenshot.bitmap.length;i++){
                int j = (screenshot.bitmap[i] >> 24 & 0xFF);
                if(i < 10){
                    System.out.println(j+"\t");
                }
                fos.write(j);
            }
            //fos.write(screenshot.bitmap, 0, screenshot.bitmap.length);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


