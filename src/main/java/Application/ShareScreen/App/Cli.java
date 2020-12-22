package Application.ShareScreen.App;

import Application.ShareScreen.System.Worker.CaptureWorker;
import Application.ShareScreen.System.Worker.SendWorker;

/**
 *
 * @author matrixy
 * @date 2018/8/31
 */
public class Cli extends Thread
{
    static String password;

    CaptureWorker captureWorker = null;
    SendWorker sendWorker = null;

    private void interact()
    {
        captureWorker = new CaptureWorker();
        sendWorker = new SendWorker();
        captureWorker.start();
        sendWorker.start();
        echo("start sharing the desktop...");
    }

    private void echo(String text)
    {
        System.out.println("  " + text);
    }

    @Override
    public void run()
    {
        try
        {
            interact();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    static Cli instance = null;
    public static void init()
    {
        instance = new Cli();
        instance.start();
    }
}
