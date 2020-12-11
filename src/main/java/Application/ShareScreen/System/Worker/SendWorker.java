package Application.ShareScreen.System.Worker;

import Application.ShareScreen.System.Screenshot;

/**
 * @author kikukk
 */
public class SendWorker extends BaseWorker{
    Screenshot lastScreen = null;           // 上一屏的截屏，用于比较图像差
    int sequence = 0;
    long lastSentTime = 0L;

    @Override
    public void run(){
        while (!this.isTerminated())
        {
            try
            {
                if (System.currentTimeMillis() - lastSentTime > 20)
                {
                    lastSentTime = System.currentTimeMillis();

                    //WsSessionManager.getInstance().broadcast(null, LocalComputer.getPointer());
                }
                //compress();
//                sleep(1);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }

}
