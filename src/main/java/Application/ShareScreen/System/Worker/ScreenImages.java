package Application.ShareScreen.System.Worker;


import Application.ShareScreen.System.Screenshot;

import java.util.LinkedList;

/**
 *
 * @author matrixy
 * @date 2018/4/9
 */
public final class ScreenImages
{
    static final LinkedList<Screenshot> SCREENSHOT_IMAGES = new LinkedList<Screenshot>();

    // 原始截图相关
    public static void addScreenshot(Screenshot screenshot)
    {
        synchronized (SCREENSHOT_IMAGES)
        {
            SCREENSHOT_IMAGES.addLast(screenshot);
        }
    }

    public static Screenshot getScreenshot()
    {
        synchronized (SCREENSHOT_IMAGES)
        {
            if (SCREENSHOT_IMAGES.size() == 0){ return null;}
            return SCREENSHOT_IMAGES.removeFirst();
        }
    }

    public static boolean hasScreenshots()
    {
        return SCREENSHOT_IMAGES.size() > 0;
    }

}
