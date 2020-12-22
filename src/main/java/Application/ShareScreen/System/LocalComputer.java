package Application.ShareScreen.System;

import java.awt.*;

/**
 *
 * @author matrixy
 * @date 2018/4/9
 */
public class LocalComputer
{
    static Robot robot = null;
    static String osname = "unknown";

    /**
     * 创建整屏截图
     */
    public static Screenshot captureScreen()
    {
        return new Screenshot(robot.createScreenCapture(getScreenSize()));
    }


    /**
     * 获取屏幕分辨率
     */
    public static Rectangle getScreenSize()
    {
        return new Rectangle(0,0,640,480);
//        return new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    }

    public static void init()
    {
        try
        {
            robot = new Robot();
            osname = System.getProperty("os.name");
        }
        catch(AWTException ex)
        {
            throw new RuntimeException(ex);
        }
    }
}
